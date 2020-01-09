package com.hzm.service;

import com.hzm.dao.ChannelDao;
import com.hzm.entity.ChannelEntity;
import com.hzm.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelDao channelDao;

    @Autowired
    IdWorker idWorker;

    public List<ChannelEntity> findAll(){
        return channelDao.findAll();
    }

    public ChannelEntity findById(String id){
        return channelDao.findById(id).get();//1.8后查询返回容器类，可以判断是否存在值或者直接get取值
    }

    public void save(ChannelEntity channel){
        channel.setId(idWorker.nextId()+"");//生成id
        channelDao.save(channel);
    }

    public void update(ChannelEntity channel){
        channelDao.save(channel);
    }

    public void deleteById(String id){
        channelDao.deleteById(id);
    }
}
