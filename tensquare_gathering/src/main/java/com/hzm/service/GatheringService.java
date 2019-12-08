package com.hzm.service;

import com.hzm.dao.GatheringDao;
import com.hzm.entity.GatheringEntity;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatheringService {

    @Autowired
    private GatheringDao gatheringDao;
    @Autowired
    IdWorker idWorker;

    public List<GatheringEntity> findAll(){
        return gatheringDao.findAll();
    }

    @Cacheable(value = "gathering",key = "#id")//往缓存中的gathering全局变量中存入一个key
    public GatheringEntity findById(String id){
        return gatheringDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(GatheringEntity gathering){
        gathering.setId(idWorker.nextId()+"");//生成id
        gatheringDao.save(gathering);
    }

    //与Cacheable相反，这是删除缓存的
    @CacheEvict(value = "gathering",key = "#gathering.id")
    public void update(GatheringEntity gathering){
        gatheringDao.save(gathering);
    }

    @CacheEvict(value = "gathering",key = "#id")
    public void deleteById(String id){
        gatheringDao.deleteById(id);
    }
}
