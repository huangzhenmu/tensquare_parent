package com.hzm.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class FriendKey implements Serializable {
    @Column(name = "userid")
    private String userid;
    @Column(name = "friendid")
    private String friendid;//好友id

    @Override
    public String toString() {
        return "FriendKey{" +
                "userid='" + userid + '\'' +
                ", friendid='" + friendid + '\'' +
                '}';
    }
}
