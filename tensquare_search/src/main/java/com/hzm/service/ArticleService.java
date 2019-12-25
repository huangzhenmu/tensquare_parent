package com.hzm.service;

import com.hzm.dao.ArticleDao;
import com.hzm.entity.Article;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /*@Autowired
    private IdWorker idWorker;*/

    public void  add(Article article){
        articleDao.save(article);//用的是es生成的id
    }

    public Page<Article> findByKey(String key, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return articleDao.findByTitleOrContentLike(key,key,pageable);
    }
}
