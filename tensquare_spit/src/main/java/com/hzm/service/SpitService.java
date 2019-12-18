package com.hzm.service;

import com.hzm.dao.SpitDao;
import com.hzm.entity.Spit;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findByid(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");
        //如果添加的吐槽有父节点，父节点的回复数加一
        if (spit.getParentid() != null && !"".equals(spit.getParentid())){
            //封装查询
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            //更新操作
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentid(String parentid,int page,int size){
        Pageable pageable = PageRequest.of(page-1,size);
        return spitDao.findByParentid(parentid,pageable);
    }


    public void thumbup(String spitId) {
        //jpa写法
        /*Spit spit =  spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup() == null? 1 : spit.getThumbup()+1);
        spitDao.save(spit);*/
        //原生mongodb实现。db.spit.update({"_id":spitId},{$inc:{thumbup:NumberInt(1)}})。inc是自增
        //对应查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        //对应更新的操作
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
