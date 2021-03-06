package com.hzm.controller;

import com.hzm.client.LabelClient;
import com.hzm.entity.PageResult;
import com.hzm.entity.ProblemEntity;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.ProblemService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "ProblemController",produces = "问答接口")
@RestController
@CrossOrigin//跨域
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LabelClient labelClient;

    @ApiOperation("分页查询新问题列表")
    @GetMapping("/newlist/{labelid}/{page}/{size}")
    public Result newList(@PathVariable("labelid") String labelid,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<ProblemEntity> pageData = problemService.newList(labelid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<ProblemEntity>(pageData.getTotalElements(),pageData.getContent()));
    }

    @ApiOperation("分页查询热门问题列表")
    @GetMapping("/hotlist/{labelid}/{page}/{size}")
    public Result hotList(@PathVariable("labelid") String labelid,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<ProblemEntity> pageData = problemService.hotList(labelid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<ProblemEntity>(pageData.getTotalElements(),pageData.getContent()));
    }

    @GetMapping("/waitlist/{labelid}/{page}/{size}")
    public Result waitList(@PathVariable("labelid") String labelid,@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        Page<ProblemEntity> pageData = problemService.waitList(labelid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<ProblemEntity>(pageData.getTotalElements(),pageData.getContent()));
    }

    @ApiOperation("查询所有")
    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",problemService.findAll());
    }

    @GetMapping("/{problemid}")
    public Result findById(@PathVariable("problemid") String problemid){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findById(problemid));
    }

    @PostMapping("/save")
    public Result save(@RequestBody ProblemEntity problemEntity){
        Claims claims = (Claims)request.getAttribute("user_claim");
        if (claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"无权限操作");
        }
        problemService.save(problemEntity);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/update/{problemid}")
    public Result update(@PathVariable("problemid") String problemid,@RequestBody ProblemEntity enterprise){
        enterprise.setId(problemid);
        problemService.update(enterprise);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/delete/{problemid}")
    public Result deleteById(@PathVariable("problemid") String problemid){
        problemService.deleteById(problemid);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @ApiOperation("查询label")
    @RequestMapping(value = "/label/{labelid}",method = RequestMethod.GET)
    public Result findLabelById(@PathVariable@ApiParam("标签id") String labelid){
        Result result = labelClient.findById(labelid);
        return result;
    }

}
