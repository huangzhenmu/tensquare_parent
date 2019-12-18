package com.hzm.controller;

import com.hzm.entity.PageResult;
import com.hzm.entity.Result;
import com.hzm.entity.Spit;
import com.hzm.entity.StatusCode;
import com.hzm.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("getAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findByid(@PathVariable("spitId") String spitId){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findByid(spitId));
    }

    @PostMapping("/add")
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"新增成功");
    }

    @PutMapping("/update/{spitId}")
    public Result update(@RequestBody Spit spit,@PathVariable("spitId") String spitId){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @DeleteMapping("/{spitId}")
    public Result deleteById(@PathVariable("spitId") String spitId){
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable("parentid") String parentid,@PathVariable("page") int page,
                          @PathVariable("size") int size){
        Page<Spit> data = spitService.findByParentid(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(data.getTotalElements(),data.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable("spitId") String spitId){
        //TODO 还没有做认证，先写死用户id
        String userid = "111";
        //判断有无店点赞
        if (redisTemplate.opsForValue().get("thumbup_"+userid) != null){
            return new Result(false,StatusCode.REPERROR,"不能重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid,1);//设置已经点赞
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}
