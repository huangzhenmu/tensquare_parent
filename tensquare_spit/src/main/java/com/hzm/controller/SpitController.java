package com.hzm.controller;

import com.hzm.entity.Result;
import com.hzm.entity.Spit;
import com.hzm.entity.StatusCode;
import com.hzm.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

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
}
