package com.hzm.controller;

import com.hzm.entity.GatheringEntity;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gathering")
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",gatheringService.findAll());
    }

    @GetMapping("/{gatheringId}")
    public Result findById(@PathVariable("gatheringId") String gatheringId){
        return new Result(true,StatusCode.OK,"查询成功",gatheringService.findById(gatheringId));
    }

    @PostMapping("/save")
    public Result save(@RequestBody GatheringEntity gathering){
        gatheringService.save(gathering);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/update/{gatheringId}")
    public Result update(@PathVariable("gatheringId") String gatheringId,@RequestBody GatheringEntity gathering){
        gathering.setId(gatheringId);
        gatheringService.update(gathering);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @DeleteMapping("/delete/{gatheringId}")
    public Result deleteById(@PathVariable("gatheringId") String gatheringId){
        gatheringService.deleteById(gatheringId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
