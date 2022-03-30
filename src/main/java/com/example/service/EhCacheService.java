package com.example.service;

import com.example.entity.User;

/**
 * author: zy
 * date: 2022/3/29 17:19
 * qq:546359148
 */
public interface EhCacheService {

    //测试失效的情况,有效期为5秒
    /*String getTimestamp(String param);

    String getDataFromDB(String key);

    void removeDataAtDB(String key);

    String refreshData(String key);*/

    User findById(String userId);

    boolean isReserved(String userId);

    String refreshData(String key);
}
