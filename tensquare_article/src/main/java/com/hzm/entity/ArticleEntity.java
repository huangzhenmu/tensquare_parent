package com.hzm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_article")
public class ArticleEntity {
    @Id
    private String id;
    private String columnid;//专栏id
    private String userid;//用户id
    private String title;//文章标题
    private String content;//文章封面
    private String image;//图片
    private String createtime;//创建时间
    private String updatetime;//更新时间
    private String ispublic;//是否公开
    private String istop;//是否指定
    private String visits;//浏览量
    private String thumbup;//点赞数
    private String comment;//评论数
    private String state;//审核状态
    private String channelid;//所属频道
    private String url;//url地址
    private String type;//文章类型
}
