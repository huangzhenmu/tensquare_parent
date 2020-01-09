package com.hzm.controller;

import com.hzm.entity.Label;
import com.hzm.entity.PageResult;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "LabelController",description = "标签管理")
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation("查询所有")
    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @ApiOperation("根据id查询标签")
    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId){
        System.out.println("No.1");
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @ApiOperation("新增标签")
    @PostMapping("/save")
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @ApiOperation("更新标签")
    @PutMapping("/update/{labelId}")
    public Result update(@PathVariable("labelId") String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @ApiOperation("删除标签")
    @DeleteMapping("/delete/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @ApiOperation("根据条件查询")
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @ApiOperation("条件查询分页")
    @PostMapping(value = "/search/{page}/{size}")
    public Result findQuery(@RequestBody Label label, @PathVariable("page")@ApiParam("页码") Integer page, @PathVariable("size")@ApiParam("页数") Integer size){
        Page<Label> labelPage = labelService.findQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(labelPage.getTotalElements(),labelPage.getContent()));
    }
}
