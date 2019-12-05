package com.hzm.dao;

import com.hzm.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReplyDao extends JpaRepository<ReplyEntity,String>, JpaSpecificationExecutor<ReplyEntity> {
}
