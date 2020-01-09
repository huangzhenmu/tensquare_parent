package com.hzm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class Spit implements Serializable {
    @Id
    private String _id;//主键必须是_id  mongodb规定！！
    private String content;//内容
    private Date publishtime;//发布时间
    private String userid;//用户id
    private String nickname;//昵称
    private Integer visits;//浏览数
    private Integer thumbup;//点赞数
    private Integer share;//分享数
    private Integer comment;//评论数
    private String state;//状态
    private String parentid;//父回复id
}
