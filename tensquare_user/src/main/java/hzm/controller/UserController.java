package hzm.controller;

import com.hzm.entity.Result;
import hzm.entity.User;
import com.hzm.entity.StatusCode;
import hzm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("getAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",userService.findAll());
    }

    @GetMapping("/{userId}")
    public Result findByid(@PathVariable("userId") String userId){
        return new Result(true, StatusCode.OK,"查询成功",userService.findByid(userId));
    }

    @PostMapping("/add")
    public Result save(@RequestBody User user){
        userService.save(user);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    @PutMapping("/update/{userId}")
    public Result update(@RequestBody User user,@PathVariable("userId") String userId){
        user.setId(userId);
        userService.update(user);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @DeleteMapping("/{userId}")
    public Result deleteById(@PathVariable("userId") String userId){
        userService.deleteById(userId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value="/sendsms/{mobile}",method=RequestMethod.POST)
    public Result sendsms(String mobile){
        userService.sendSms(mobile);
        return new Result(true,StatusCode.OK,"发送成功");
    }

    /**    
      * 用户注册    
      * @param user    
      */
    @RequestMapping(value="/register/{code}",method=RequestMethod.POST)
    public Result register(@RequestBody User user,@PathVariable String code){
        userService.add(user,code);
        return new Result(true,StatusCode.OK,"注册成功");
    }

}
