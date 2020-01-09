package com.hzm.dao;

import com.hzm.entity.ProblemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemDao extends JpaRepository<ProblemEntity,String>, JpaSpecificationExecutor<ProblemEntity> {

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ?1 order by replytime DESC ",nativeQuery = true)
    public Page<ProblemEntity> newList(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ?1 order by reply DESC ",nativeQuery = true)
    public Page<ProblemEntity> hotList(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ?1 and reple = 0 order by createtime DESC ",nativeQuery = true)
    public Page<ProblemEntity> waitList(String labelid, Pageable pageable);
}

