package com.example.service.impl;

import com.example.entity.User;
import com.example.service.EhCacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * author: zy
 * date: 2022/3/29 17:22
 * qq:546359148
 */
@Service
public class EhCacheServiceImpl implements EhCacheService {

    @Override
    /*key默认是方法的参数,value对应缓存位置的名称*/
    @Cacheable(value="UserCache",keyGenerator = "selfKeyGenerate")  //'user:'+#userId 的意思就是: user:1000
    public User findById(String userId) {
        System.out.println("execute findById......");
        return new User("1000","Jack");
    }

    //将缓存保存进UserCache中，并当参数userId的长度小于12时才保存进缓存
    @Override
    @Cacheable(value = "UserCache",condition = "#userId.length()<12")
    public boolean isReserved(String userId){
        System.out.println("UserCache:"+userId);
        return false;  //value就是返回值
    }

    /**
     * @param key 参数
     * @return 返回的是返回值即key+"::"+String.valueOf(Math.round(Math.random()*1000000));
     */
    @Override
    @Cacheable(value="UserCache",key="#key")
    public String refreshData(String key){
        System.out.println("模拟从数据库中加载数据...");
        return key+"::"+String.valueOf(Math.round(Math.random()*1000000));
    }

    /*@Override
    public String getTimestamp(String param) {
        return null;
    }

    @Override
    public String getDataFromDB(String key) {
        return null;
    }

    @Override
    public void removeDataAtDB(String key) {

    }

    @Override
    public String refreshData(String key) {
        return null;
    }*/

}
