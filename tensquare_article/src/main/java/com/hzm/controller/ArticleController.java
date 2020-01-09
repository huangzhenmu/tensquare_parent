package com.hzm.controller;

import com.hzm.entity.ArticleEntity;
import com.hzm.entity.Result;
import com.hzm.entity.StatusCode;
import com.hzm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/examine/{articleid}")
    public Result examine(@PathVariable("articleid") String articleid){
        articleService.updateState( articleid);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @PostMapping("/examine/{articleid}")
    public Result thumup(@PathVariable("articleid") String articleid){
        articleService.addThumup(articleid);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @GetMapping("/findAll")
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",articleService.findAll());
    }

    @GetMapping("/{articleId}")
    public Result findById(@PathVariable("articleId") String articleId){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findById(articleId));
    }

    @PostMapping("/save")
    public Result save(@RequestBody ArticleEntity article){
        articleService.save(article);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PutMapping("/update/{articleId}")
    public Result update(@PathVariable("articleId") String articleId,@RequestBody ArticleEntity article){
        article.setId(articleId);
        articleService.update(article);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @DeleteMapping("/delete/{articleId}")
    public Result deleteById(@PathVariable("articleId") String articleId){
        articleService.deleteById(articleId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
