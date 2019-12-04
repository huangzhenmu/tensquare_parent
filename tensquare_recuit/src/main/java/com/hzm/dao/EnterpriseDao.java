package com.hzm.dao;

import com.hzm.entity.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EnterpriseDao extends JpaRepository <EnterpriseEntity,String>, JpaSpecificationExecutor<EnterpriseEntity> {
    public List<EnterpriseEntity> findByIshot(String ishot);
}
