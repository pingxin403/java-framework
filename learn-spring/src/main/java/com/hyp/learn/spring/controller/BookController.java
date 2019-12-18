package com.hyp.learn.spring.controller;

import com.hyp.learn.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

}
