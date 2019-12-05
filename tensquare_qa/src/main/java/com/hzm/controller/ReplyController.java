package com.hzm.controller;

import com.hzm.entity.ReplyEntity;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin//跨域
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",replyService.findAll());
    }

    @GetMapping("/{replyid}")
    public Result findById(@PathVariable("replyid") String replyid){
        return new Result(true,StatusCode.OK,"查询成功",replyService.findById(replyid));
    }

    @PostMapping("/save")
    public Result save(@RequestBody ReplyEntity replyEntity){
        replyService.save(replyEntity);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/update/{replyid}")
    public Result update(@PathVariable("replyid") String replyid,@RequestBody ReplyEntity enterprise){
        enterprise.setId(replyid);
        replyService.update(enterprise);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/delete/{replyid}")
    public Result deleteById(@PathVariable("replyid") String replyid){
        replyService.deleteById(replyid);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
