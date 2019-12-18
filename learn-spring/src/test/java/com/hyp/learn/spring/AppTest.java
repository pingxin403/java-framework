package com.hyp.learn.spring;

import com.hyp.learn.spring.demo1.BookService;
import com.hyp.learn.spring.demo1.UserService;
import com.hyp.learn.spring.demo2.Creature;
import com.hyp.learn.spring.demo3.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void demo01() {
        //从spring容器获得
        //1 获得容器
        String xmlPath = "applicationContext01.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //2获得内容 --不需要自己new，都是从spring容器获得
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.addUser();
    }

    @Test
    public void demo02() {
        //从spring容器获得
        String xmlPath = "applicationContext01.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        BookService bookService = (BookService) applicationContext.getBean("bookService");

        bookService.addBook();//di  add book
    }

    @Test
    public void demo03() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext01.xml"
        );
        Creature person = context.getBean("person", Creature.class);
        person.useTool();
    }

    @Test
    public void demo04() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext01.xml"
        );
//        Book book = context.getBean("book1", Book.class);
//        Book book = context.getBean("book2", Book.class);
//        System.out.println(book);
//
//        Author author = context.getBean("author2", Author.class);
//        System.out.println(author);

//        Book book3 = context.getBean("book3", Book.class);
//        System.out.println(book3);

        Book book4 = context.getBean("book4", Book.class);
        System.out.println(book4);
    }

    @Test
    public void demo05() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext01.xml"
        );


    }


}
