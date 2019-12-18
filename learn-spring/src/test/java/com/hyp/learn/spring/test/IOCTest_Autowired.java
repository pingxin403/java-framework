package com.hyp.learn.spring.test;

import com.hyp.learn.spring.bean.Boss;
import com.hyp.learn.spring.bean.Car;
import com.hyp.learn.spring.bean.Color;
import com.hyp.learn.spring.config.MainConifgOfAutowired;
import com.hyp.learn.spring.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);
		
		BookService bookService = applicationContext.getBean(BookService.class);
		System.out.println(bookService);
		
		//BookDao bean = applicationContext.getBean(BookDao.class);
		//System.out.println(bean);
		
		Boss boss = applicationContext.getBean(Boss.class);
		System.out.println(boss);
		Car car = applicationContext.getBean(Car.class);
		System.out.println(car);
		
		Color color = applicationContext.getBean(Color.class);
		System.out.println(color);
		System.out.println(applicationContext);
		applicationContext.close();
	}

}
