package com.hzm.dao;

import com.hzm.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColumnDao extends JpaRepository<ColumnEntity,String> , JpaSpecificationExecutor<ColumnEntity> {
}
