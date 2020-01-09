package hzm.service;

import com.hzm.util.IdWorker;
import hzm.dao.AdminDao;
import hzm.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Admin> findAll(){
        return adminDao.findAll();
    }

    public Admin findByid(String id){
        return adminDao.findById(id).get();
    }

    public void save(Admin admin){
        admin.setId(idWorker.nextId()+"");
        //密码加密
        String ecryptPassword = encoder.encode(admin.getPassword());
        admin.setPassword(ecryptPassword);
        adminDao.save(admin);
    }

    public void update(Admin admin){
        adminDao.save(admin);
    }

    public void deleteById(String id){
        adminDao.deleteById(id);
    }

    public Admin loginByLoginnameAndPassword(String loginname,String password){
        Admin admin = adminDao.findByLoginname(loginname);
        if (admin != null && encoder.matches(password,admin.getPassword())){
            return admin;
        }else {
            return null;
        }
    }
}
