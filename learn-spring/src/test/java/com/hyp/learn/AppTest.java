package com.hyp.learn;

import static org.junit.Assert.assertTrue;

import com.hyp.learn.spring.demo1.BookService;
import com.hyp.learn.spring.demo1.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void demo01(){
        //从spring容器获得
        //1 获得容器
        String xmlPath = "applicationContext01.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //2获得内容 --不需要自己new，都是从spring容器获得
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.addUser();
    }

    @Test
    public void demo02(){
        //从spring容器获得
        String xmlPath = "applicationContext01.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        BookService bookService = (BookService) applicationContext.getBean("bookService");

        bookService.addBook();//di  add book
    }

    @Test
    public void demo03(){
        BeanFactory beanFactory;
        ApplicationContext applicationContext;
    }


}
