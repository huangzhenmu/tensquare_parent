package com.hzm.entity;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ColumnEntity {
    @Id
    private String id;
}
