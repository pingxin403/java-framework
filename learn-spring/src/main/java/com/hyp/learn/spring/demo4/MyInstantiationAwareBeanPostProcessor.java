package com.hyp.learn.spring.demo4;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo4
 * hyp create at 19-12-15
 **/
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("[MyInstantiationAwareBeanPostProcessor] constructor.");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessor] postProcessBeforeInstantiation");
        return null;
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessor] postProcessAfterInstantiation");
        return true;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessor] postProcessPropertyValues");
        // 注意返回结果，否则会无法正确Bean的属性
        return pvs;
    }
}
