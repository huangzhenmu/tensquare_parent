package hzm.dao;

import hzm.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminDao extends JpaRepository<Admin,String>, JpaSpecificationExecutor<Admin> {
    public Admin findByLoginname(String loginname);
}
