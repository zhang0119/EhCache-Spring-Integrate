package org.example.myapp;

import com.example.entity.User;
import com.example.service.EhCacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * author: zy
 * date: 2022/3/29 17:40
 * qq:546359148
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-ehcache.xml"})
public class SpringTest {

    @Autowired
    private EhCacheService ehCacheService;

    //注入cacheManager
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testFindById(){

        //这里我调用两次方法，第二次是从缓存中取得结果，速度比第一次更快
        User byId1 = ehCacheService.findById("1000");
        System.out.println("byId1:"+byId1);

        System.out.println("---------------------------------");

        User byId2 = ehCacheService.findById("1000");
        System.out.println("byId2:"+byId2);

        System.out.println("---------------------------------");

        Cache userCache = cacheManager.getCache("UserCache");

        System.out.println(userCache.getKeys());
        /*Element element = userCache.get("user:1000");
        System.out.println(element);*/
    }

    @Test
    public void testIsReserved(){

        ehCacheService.isReserved("100000000000000000");
        System.out.println("-------------------------------------------");
        ehCacheService.isReserved("100000000000000000");
        System.out.println("-------------------------------------------");

        Cache userCache = cacheManager.getCache("UserCache");
        Element element = userCache.get("100000000000000000");
        System.out.println("element-->"+element);
    }

    @Test
    public void testCachePut(){
        String key1 = ehCacheService.refreshData("1000");
        System.out.println(key1);

        System.out.println("-----------------------------------");

        String key2 = ehCacheService.refreshData("1000");
        System.out.println(key2);

    }
}
