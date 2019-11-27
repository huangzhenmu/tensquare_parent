package com.hzm.dao;

import com.hzm.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//第二个是为了复杂查询而准备的
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
