package com.hzm.service;

import com.hzm.client.UserClient;
import com.hzm.dao.FriendDao;
import com.hzm.dao.NoFriendDao;
import com.hzm.entity.Friend;
import com.hzm.entity.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    @Transactional
    public int addFriend(String userid,String friendid){
        //判断如果用户已经添加了这个好友，则不进行任何操作,返回0
        if(friendDao.selectCount(userid, friendid)>0){
            return 0;
        }
        //向喜欢表中添加记录
        Friend friend=new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        userClient.incFollowcount(userid,1);//增加自己的关注数
        userClient.incFanscount(friendid,1);//增加对方的粉丝数
        //判断对方是否喜欢你，如果喜欢，将islike设置为1
        if(friendDao.selectCount( friendid,userid)>0){
            friendDao.updateLike(userid,friendid,"1");
            friendDao.updateLike(friendid,userid,"1");
            userClient.incFollowcount(friendid,1);//增加朋友的关注数
            userClient.incFanscount(userid,1);//增加自己的粉丝数
        }
        return 1;
    }

    /**
      * 向不喜欢列表中添加记录
      * @param userid
      * @param friendid
      */
    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }

    /**
     * 删除好友
     * @param userid
     * @param friendid
     */
    @Transactional
    public void deleteFriend(String userid,String friendid){
        friendDao.deleteFriend(userid,friendid);//删除好友表，表示用户不喜欢该好友
        friendDao.updateLike(friendid,userid,"0");//好友的喜欢变成单向
        userClient.incFollowcount(userid,-1);//减少自己的关注数
        userClient.incFanscount(friendid,-1);//减少对方的粉丝数
        addNoFriend(userid,friendid);//向不喜欢好友表中添加记录
    }
}
