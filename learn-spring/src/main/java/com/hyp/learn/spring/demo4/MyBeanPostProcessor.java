package com.hyp.learn.spring.demo4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo4
 * hyp create at 19-12-15
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {
    public MyBeanPostProcessor() {
        super();
        System.out.println("[MyBeanPostProcessor] constructor.");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyBeanPostProcessor] postProcessBeforeInitialization: " + bean.getClass() + ": " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyBeanPostProcessor] postProcessAfterInitialization: " + bean.getClass() + ": " + beanName);
        return bean;
    }
}
