package hzm.service;

import com.hzm.util.IdWorker;
import hzm.dao.UserDao;
import hzm.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findByid(String id){
        return userDao.findById(id).get();
    }

    public void save(User user){
        user.setId(idWorker.nextId()+"");
        String encryptPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptPassword);
        userDao.save(user);
    }

    public void update(User user){
        userDao.save(user);
    }

    public void deleteById(String id){
        userDao.deleteById(id);
    }

    /**
     * 发送短信验证码
     * @param mobile 手机号码
     */
    public void  sendSms(String mobile){
        //生成6位验证码
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        int code = random.nextInt(max);
        if (code<min){
            code = min+code;
        }
        logger.info(mobile+"的验证码："+code);
        //将验证码放入缓存
        redisTemplate.opsForValue().set("smscode_"+mobile,code+"",5, TimeUnit.MINUTES);//五分钟过期
        //验证码放入消息队列
        Map<String,String> map = new HashMap();
        map.put("mobile",mobile);
        map.put("code",code+"");
        rabbitTemplate.convertAndSend("sms",map);
    }

    /**
      * 增加用户
      * @param user 用户
      * @param code 用户填写的验证码
      */
    public void add(User user,String code){
        //判断验证码是否正确
        String syscode = (String)redisTemplate.opsForValue().get("smscode_" + user.getMobile());
        //提取系统正确的验证码
        if(syscode==null){
            throw new RuntimeException("请点击获取短信验证码");
        }
        if(!syscode.equals(code)){
            throw new RuntimeException("验证码输入不正确");
        }
        user.setId(idWorker.nextId()+"");
        user.setFollowcount(0);//关注数
        user.setFanscount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        userDao.save(user);
    }

    public User findByMobileAndPassword(String mobile,String password){
        User user = userDao.findByMobile(mobile);
        if (encoder.matches(password,user.getPassword())){
            return user;
        }else {
            return null;
        }
    }
}
