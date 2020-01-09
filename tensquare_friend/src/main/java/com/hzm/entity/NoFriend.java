package com.hzm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_nofriend")
@Entity(name = "好友表")
@IdClass(NoFriend.class)//复合主键
public class NoFriend implements Serializable {//一定要实现Serializable，不然报错
    @Id
    private String userid;
    @Id
    private String friendid;//好友id
}
