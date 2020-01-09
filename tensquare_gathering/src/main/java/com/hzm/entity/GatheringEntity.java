package com.hzm.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="tb_gathering")
public class GatheringEntity {
    @Id
    private String id;
}
