package com.hyp.learn.spring.demo4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.demo4
 * hyp create at 19-12-15
 **/
public class LifeCycleApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lifecycle.xml");
        System.out.println("[Application] before get bean");
        MyBean bean = (MyBean) context.getBean("myBean");
        System.out.println("[Application] after get bean");
        System.out.println(bean);
        context.registerShutdownHook();
    }
}
