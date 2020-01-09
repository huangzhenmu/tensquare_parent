package hzm.dao;

import hzm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    public User findByMobile(String mobile);

    /**
     * 更新粉丝数
     * @param userid 用户ID
     * @param x 粉丝数
     */
    @Modifying
    @Query(value = "update tb_user set fanscount = fanscount+?2 where  id = ?1",nativeQuery =  true)
    public void incFanscount(String userid,int x);

    /**
     * 更新关注数
     * @param userid 用户ID
     * @param x 粉丝数
     */
    @Modifying
    @Query(value = "update tb_user set followcount = followcount+?2 where id = ?1")
    public void incFollowcount(String userid,int x);
}
