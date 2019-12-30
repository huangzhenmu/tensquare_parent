package hzm.controller;

import com.hzm.entity.Result;
import com.hzm.util.JwtUtil;
import hzm.entity.User;
import com.hzm.entity.StatusCode;
import hzm.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtUtil jwtUtil;

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
        /*
        //未加过滤器的验证方法
        //约定请求头中的验证信息为Bearer token
        String authorization = request.getHeader("Authorization");
        //验证是否有删除用户权限
        if (authorization == null){
            return new Result(false,StatusCode.ACCESSERROR,"无该操作权限");
        }else if (!authorization.startsWith("Bearer ")){
            return new Result(false,StatusCode.ACCESSERROR,"无该操作权限");
        }
        String token = authorization.substring(7);
        Claims claims = jwtUtil.parseJwt(token);
        if (claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"无该操作权限");
        }
        if (!"admin".equals(claims.get("roles"))){
            return new Result(false,StatusCode.ACCESSERROR,"无该操作权限");
        }*/
        //加过滤器的验证方法
        Claims claims = (Claims)request.getAttribute("admin_clain");
        if (claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"无该操作权限");
        }
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

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Result login(String mobile,String password){
        User user = userService.findByMobileAndPassword(mobile, password);
        if (user != null){
            String token = jwtUtil.createJwt(user.getId(),user.getNickname(),"user");
            Map map=new HashMap();
            map.put("token",token);
            map.put("name",user.getNickname());//昵称
            map.put("avatar",user.getAvatar());//头像
            return new Result(true,StatusCode.OK,"登录成功",map);
        }else {
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
    }

}
