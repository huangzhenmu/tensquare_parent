package com.hzm.dao;

import com.hzm.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleDao extends JpaRepository<ArticleEntity,String>, JpaSpecificationExecutor<ArticleEntity> {
    @Modifying
    @Query(value = "update tb_article set state = 1 where id = ?1",nativeQuery = true)
    public void updateState(String id);

    @Modifying
    @Query(value = "update tb_article set thumbup = thumup+1 where id = ?1",nativeQuery = true)
    public void addThumbup(String id);
}
