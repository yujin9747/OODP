package com.example.demo;

import org.springframework.context.ApplicationContext;

public class BeanUtil {

    private static ApplicationContext context;

    public static void init(ApplicationContext context){ // 1. applicationContext 를 주입받을 메서드
        BeanUtil.context = context;
    }

    public static <T> T get(Class<T> clazz){
        return context.getBean(clazz);
    }
}
