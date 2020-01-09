package com.hzm.service;

import com.hzm.dao.ArticleDao;
import com.hzm.entity.ArticleEntity;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //添加事务支持
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    IdWorker idWorker;

    public void updateState(String id){
        articleDao.updateState(id);
    }

    public void addThumup(String id){
        articleDao.addThumbup(id);
    }

    public List<ArticleEntity> findAll(){
        return articleDao.findAll();
    }

    public ArticleEntity findById(String id){
        return articleDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(ArticleEntity article){
        article.setId(idWorker.nextId()+"");//生成id
        articleDao.save(article);
    }

    public void update(ArticleEntity article){
        articleDao.save(article);
    }

    public void deleteById(String id){
        articleDao.deleteById(id);
    }

}
