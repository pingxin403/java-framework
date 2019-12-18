package com.hyp.learn.spring.demo4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo4
 * hyp create at 19-12-15
 **/
public class MyBean implements
        BeanNameAware,
        BeanFactoryAware,
        ApplicationContextAware,
        BeanClassLoaderAware,
        InitializingBean,
        DisposableBean {
    private String name;
    private String description;

    public MyBean() {
        System.out.println("**MyBean** construct.");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("**MyBean** BeanClassLoaderAware.setBeanClassLoader: " + classLoader.getClass());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("**MyBean** BeanFactoryAware.setBeanFactory: " + beanFactory.getClass());
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("**MyBean** BeanNameAware.getBeanName: " + s);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("**MyBean** DisposableBean.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("**MyBean** InitializingBean.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("**MyBean** ApplicationContextAware.setApplicationContext");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("**MyBean** MyBean finalized.");
    }

    @PostConstruct
    public void springPostConstruct() {
        System.out.println("**MyBean** @PostConstruct");
    }

    // xml文件中的init-method
    public void myInitMethod() {
        System.out.println("**MyBean** init-method");
    }

    @PreDestroy
    public void springPreDestroy() {
        System.out.println("**MyBean** @PreDestroy");
    }

    // xml文件中的destroy-method
    public void mydestroyMethod() {
        System.out.println("**MyBean** destory-method");
    }

    @Autowired
    public void setName(String name) {
        this.name = name;
        System.out.println("**MyBean** setName");
    }

    @Autowired
    public void setDescription(String description) {
        this.description = description;
        System.out.println("**MyBean** setDescription");
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

