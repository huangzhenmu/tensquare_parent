package hzm.controller;

import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.util.JwtUtil;
import hzm.entity.Admin;
import hzm.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "AdminController",description = "管理员")
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("getAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",adminService.findAll());
    }

    @GetMapping("/{adminId}")
    public Result findByid(@PathVariable("adminId") String adminId){
        return new Result(true, StatusCode.OK,"查询成功",adminService.findByid(adminId));
    }

    @PostMapping("/add")
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    @PutMapping("/update/{adminId}")
    public Result update(@RequestBody Admin admin,@PathVariable("adminId") String adminId){
        admin.setId(adminId);
        adminService.update(admin);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @DeleteMapping("/{adminId}")
    public Result deleteById(@PathVariable("adminId") String adminId){
        adminService.deleteById(adminId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,String> loginMap){
        String loginname = loginMap.get("loginname");
        String password = loginMap.get("password");
        Admin admin = adminService.loginByLoginnameAndPassword(loginname,password);
        if (admin != null){
            String token = jwtUtil.createJwt(admin.getId(),admin.getLoginname(),"admin");
            Map map = new HashMap();
            map.put("token",token);
            map.put("name",admin.getLoginname());//登陆名
            return new Result(true,StatusCode.OK,"登陆成功",map);
        }else {
            return new Result(false,StatusCode.LOGINERROR,"用户名或密码错误");
        }
    }
}
