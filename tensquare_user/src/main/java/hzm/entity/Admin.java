package hzm.entity;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "tb_admin")
@Data
public class Admin {
    @Id
    private String id;//
    private String loginname;//登录名称
    private String password;//密码
    private String state;//状态
}
