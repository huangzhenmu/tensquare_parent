package com.hzm.controller;

import com.hzm.entity.PageResult;
import com.hzm.entity.RecuitEntity;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.RecuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recuit")
public class RecuitController {

    @Autowired
    private RecuitService recuitService;

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",recuitService.findAll());
    }

    @GetMapping("/{recuitId}")
    public Result findById(@PathVariable("recuitId") String recuitId){
        return new Result(true,StatusCode.OK,"查询成功",recuitService.findById(recuitId));
    }

    @PostMapping("/save")
    public Result save(@RequestBody RecuitEntity recuit){
        recuitService.save(recuit);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/update/{recuitId}")
    public Result update(@PathVariable("recuitId") String recuitId,@RequestBody RecuitEntity recuit){
        recuit.setId(recuitId);
        recuitService.update(recuit);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @DeleteMapping("/delete/{recuitId}")
    public Result deleteById(@PathVariable("recuitId") String recuitId){
        recuitService.deleteById(recuitId);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /*@PostMapping(value = "/search")
    public Result findSearch(@RequestBody RecuitEntity recuit){
        List<RecuitEntity> list = recuitService.findSearch(recuit);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result findQuery(@RequestBody RecuitEntity recuit,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<RecuitEntity> recuitPage = recuitService.findQuery(recuit,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<RecuitEntity>(recuitPage.getTotalElements(),recuitPage.getContent()));
    }*/
}
