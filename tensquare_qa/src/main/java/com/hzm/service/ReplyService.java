package com.hzm.service;

import com.hzm.dao.ReplyDao;
import com.hzm.entity.ReplyEntity;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    IdWorker idWorker;

    public List<ReplyEntity> findAll(){
        return replyDao.findAll();
    }

    public ReplyEntity findById(String id){
        return replyDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(ReplyEntity reply){
        reply.setId(idWorker.nextId()+"");//生成id
        replyDao.save(reply);
    }

    public void update(ReplyEntity reply){
        replyDao.save(reply);
    }

    public void deleteById(String id){
        replyDao.deleteById(id);
    }
}
