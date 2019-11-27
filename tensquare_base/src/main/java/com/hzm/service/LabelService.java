package com.hzm.service;

import com.hzm.dao.LabelDao;
import com.hzm.entity.Label;
import com.hzm.util.IdWorker;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    IdWorker idWorker;
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");//生成id
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }
}