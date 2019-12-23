package com.hyp.learn.mybatis.dao;

import com.hyp.learn.mybatis.pojo.User;

import java.util.List;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.dao
 * hyp create at 19-12-22
 **/
public interface UserMapper {

    public Integer saveUser(User user) throws Exception;

    public List<User> selectAllUser() throws Exception;

    public User findUserById(Integer id) throws Exception;

    public List<User> findUsersByName(String name) throws Exception;

    public Integer updateUser(User user) throws Exception;

    public Integer deleteUser(Integer id) throws Exception;

}
