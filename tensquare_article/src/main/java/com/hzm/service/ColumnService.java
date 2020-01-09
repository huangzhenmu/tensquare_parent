package com.hzm.service;

import com.hzm.dao.ChannelDao;
import com.hzm.dao.ColumnDao;
import com.hzm.entity.ColumnEntity;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {
    @Autowired
    private ColumnDao columnDao;

    @Autowired
    IdWorker idWorker;

    public List<ColumnEntity> findAll(){
        return columnDao.findAll();
    }

    public ColumnEntity findById(String id){
        return columnDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(ColumnEntity column){
        column.setId(idWorker.nextId()+"");//生成id
        columnDao.save(column);
    }

    public void update(ColumnEntity column){
        columnDao.save(column);
    }

    public void deleteById(String id){
        columnDao.deleteById(id);
    }
}
