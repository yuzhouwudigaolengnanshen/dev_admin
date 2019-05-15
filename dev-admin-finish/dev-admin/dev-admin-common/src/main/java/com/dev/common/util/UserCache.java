package com.dev.common.util;


import com.dev.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.stereotype.Component;

/**
 * 类描述
 * Created by 风象南(yuboon) on 2019/03/03
 */
@Component
public class UserCache {

    private String cacheGroup = "userCache";
    private String cacheName = "currentUser";

    @Autowired
    private JCacheCacheManager cacheManager;

    public void put(User user){
        cacheManager.getCache(cacheGroup).put(cacheName,user);
    };

    public User get(){
        User user = cacheManager.getCache(cacheGroup).get(cacheName,User.class);
        return user;
    }
}
