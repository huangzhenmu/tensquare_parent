package com.hzm.dao;

import com.hzm.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleDao extends ElasticsearchRepository<Article,String > {
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
