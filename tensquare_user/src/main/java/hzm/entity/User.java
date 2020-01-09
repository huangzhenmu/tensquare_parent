package hzm.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity()
@Table(name = "tb_user")
@Data
public class User implements Serializable {
    @Id
    private String id;//主键id
    private String mobile;//手机号
    private String password;//密码
    private String nickname;//昵称
    private String sex;//性别
    private Date birthday;//生日
    private String avatar;//头像
    private String email;//email
    private Date regdate;//注册日期
    private Date updatedate;//修改日期
    private Date lastdate;//最后登录日期
    private Long online;//在线时长（分钟）
    private String interest;//兴趣
    private String personality;//个性
    private Integer fanscount;//粉丝数
    private Integer followcount;//关注数
}
