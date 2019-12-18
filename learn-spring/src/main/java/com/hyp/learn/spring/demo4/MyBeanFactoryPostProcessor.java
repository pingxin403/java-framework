package com.hyp.learn.spring.demo4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo4
 * hyp create at 19-12-15
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public MyBeanFactoryPostProcessor() {
        super();
        System.out.println("[MyBeanFactoryPostProcessor] constructor");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("[MyBeanFactoryPostProcessor] postProcessBeanFactory");
    }
}

