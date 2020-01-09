package com.hzm.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "tb_friend")
@Entity(name = "好友表")
@IdClass(Friend.class)//复合主键
public class Friend implements Serializable {
    /*@EmbeddedId
    private FriendKey id;*/
    @Id
    private String userid;
    @Id
    private String friendid;//好友id
    private String islike;//0表示单向喜欢，1表示双向喜欢

}
