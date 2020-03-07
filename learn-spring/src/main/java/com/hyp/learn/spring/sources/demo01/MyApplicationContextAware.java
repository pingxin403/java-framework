package com.hyp.learn.spring.sources.demo01;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.sources.demo01
 * hyp create at 19-12-24
 **/
public class MyApplicationContextAware implements ApplicationContextAware {
    private ApplicationContext applicationContext; // 自动装配时被忽略注入

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}