package com.example.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * author: zy
 * date: 2022/3/29 19:56
 * qq:546359148
 */
@Component("selfKeyGenerate")
public class SelfKeyGenerate implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object...params) {
        return target.getClass().getSimpleName()+"#"+method.getName();
    }
}
