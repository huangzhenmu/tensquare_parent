package com.hzm.controller;

import com.hzm.entity.EnterpriseEntity;
import com.hzm.entity.PageResult;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",enterpriseService.findAll());
    }

    @GetMapping("/{enterpriseId}")
    public Result findById(@PathVariable("enterpriseId") String enterpriseId){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findById(enterpriseId));
    }

    @PostMapping("/save")
    public Result save(@RequestBody EnterpriseEntity enterprise){
        enterpriseService.save(enterprise);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/update/{enterpriseId}")
    public Result update(@PathVariable("enterpriseId") String enterpriseId,@RequestBody EnterpriseEntity enterprise){
        enterprise.setId(enterpriseId);
        enterpriseService.update(enterprise);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/delete/{enterpriseId}")
    public Result deleteById(@PathVariable("enterpriseId") String enterpriseId){
        enterpriseService.deleteById(enterpriseId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @PostMapping("/search/hostlist")
    public Result hotlist(){
        List<EnterpriseEntity> list = enterpriseService.hotList("1");
        return new Result(true,StatusCode.OK,"查询成功",list);
    }
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody EnterpriseEntity enterprise){
        List<EnterpriseEntity> list = enterpriseService.findSearch(enterprise);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result findQuery(@RequestBody EnterpriseEntity enterprise,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<EnterpriseEntity> enterprisePage = enterpriseService.findQuery(enterprise,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<EnterpriseEntity>(enterprisePage.getTotalElements(),enterprisePage.getContent()));
    }
}
