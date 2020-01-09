package com.hzm.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ChannelEntity {
    @Id
    private String id;
}
