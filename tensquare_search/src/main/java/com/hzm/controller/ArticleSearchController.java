package com.hzm.controller;

import com.hzm.entity.Article;
import com.hzm.entity.PageResult;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleService.add(article);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{key}/{page}/{size}",method = RequestMethod.GET)
    public Result findByKey(@PathVariable String key,@PathVariable Integer page,@PathVariable Integer size){
        Page<Article> pageDate = articleService.findByKey(key,page,size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Article>(pageDate.getTotalElements(),pageDate.getContent()));
    }
}
