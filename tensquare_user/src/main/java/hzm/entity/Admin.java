package hzm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Admin {
    @Id
    private String id;//
    private String loginname;//登录名称
    private String password;//密码
    private String state;//状态
}
