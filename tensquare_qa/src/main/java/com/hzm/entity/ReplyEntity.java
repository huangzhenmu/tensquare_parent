package com.hzm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_reply")
@Data
public class ReplyEntity {
    @Id
    private String id;//id
    private String problemid;//问题id
    private String content;//回答内容
    private String createtime;//回答日期
    private String updatetime;//更新时间
    private String userid;//回答人id
    private String nickname;//回答人昵称

}
