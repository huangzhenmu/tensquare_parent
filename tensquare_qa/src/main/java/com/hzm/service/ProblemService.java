package com.hzm.service;

import com.hzm.dao.ProblemDao;
import com.hzm.entity.ProblemEntity;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    IdWorker idWorker;

    public List<ProblemEntity> findAll(){
        return problemDao.findAll();
    }

    public ProblemEntity findById(String id){
        return problemDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(ProblemEntity problem){
        problem.setId(idWorker.nextId()+"");//生成id
        problemDao.save(problem);
    }

    public void update(ProblemEntity problem){
        problemDao.save(problem);
    }

    public void deleteById(String id){
        problemDao.deleteById(id);
    }

    public Page<ProblemEntity> newList(String labelid, int page, int rows){
        Pageable pageable = PageRequest.of(page-1,rows);
        return problemDao.newList(labelid,pageable);
    }

    public Page<ProblemEntity> hotList(String labelid, int page, int rows){
        Pageable pageable = PageRequest.of(page-1,rows);
        return problemDao.hotList(labelid,pageable);
    }
    public Page<ProblemEntity> waitList(String labelid, int page, int rows){
        Pageable pageable = PageRequest.of(page-1,rows);
        return problemDao.waitList(labelid,pageable);
    }
}
