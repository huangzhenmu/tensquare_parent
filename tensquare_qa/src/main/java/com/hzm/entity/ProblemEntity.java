package com.hzm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_problem")
@Data
public class ProblemEntity {
    @Id
    private String id;//id
    private String title;//问题标题
    private String content;//问题内容
    private Date createtime;//发布日期
    private Date updatetime;//更新日期
    private String userid;//发布人id
    private String nickname;//发布人昵称
    private Integer visits;//浏览量
    private Integer thumbup;//点赞数
    private Integer reply;//回复数
    private String solve;//是否解决
    private String replyname;//最新回复人
    private String replytime;//最新回复时间
}
