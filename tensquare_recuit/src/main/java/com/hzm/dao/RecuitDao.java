package com.hzm.dao;

import com.hzm.entity.RecuitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecuitDao extends JpaRepository<RecuitEntity,String >, JpaSpecificationExecutor<RecuitEntity> {
    public List<RecuitEntity> findTop6ByStateOrderByCreatetime(String state);

    public List<RecuitEntity> findTop6ByStateNotOrderByCreatetime(String state);
}
