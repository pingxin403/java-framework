package com.hyp.learn.spring.demo1;

import com.hyp.learn.spring.demo1.UserService;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.spring.service.impl
 * hyp create at 19-12-15
 **/
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("a_ico add user");
    }
}
