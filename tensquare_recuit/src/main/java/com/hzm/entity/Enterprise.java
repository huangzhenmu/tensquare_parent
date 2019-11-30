package com.hzm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_enterprise")
@Data
public class Enterprise {
    @Id
    private String id;//主键id
    private String name;//企业名称
    private String summary;//企业简介
    private String address;//企业地址
    private String lebels;//标签列表
    private String coordinate;//坐标
    private String ishot;//是否热门
    private String logo;//LOGO
    private String jobcount;//职位数
    private String url;//网址

}
