package com.hyp.learn.spring.sources.demo01;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.sources.demo01
 * hyp create at 19-12-24
 **/
public class MyBeanFactoryAware implements BeanFactoryAware {
    private BeanFactory beanFactory; // 自动装配时忽略注入

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
