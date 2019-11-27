package com.hzm.controller;

import com.hzm.entity.Label;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/update/{labelId}")
    public Result update(@PathVariable("labelId") String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @DeleteMapping("/delete/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"添加成功");
    }
}
