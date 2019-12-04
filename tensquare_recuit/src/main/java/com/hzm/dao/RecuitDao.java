package com.hzm.dao;

import com.hzm.entity.RecuitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RecuitDao extends JpaRepository<RecuitEntity,String >, JpaSpecificationExecutor<RecuitEntity> {
}
