package com.hzm.dao;

import com.hzm.entity.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChannelDao extends JpaRepository<ChannelEntity,String>, JpaSpecificationExecutor<ChannelEntity> {
}
