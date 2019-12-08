package com.hzm.dao;

import com.hzm.entity.GatheringEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GatheringDao extends JpaRepository<GatheringEntity,String> , JpaSpecificationExecutor<GatheringEntity> {
}
