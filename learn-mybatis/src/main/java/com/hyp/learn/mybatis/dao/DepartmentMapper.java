package com.hyp.learn.mybatis.dao;

import com.hyp.learn.mybatis.pojo.Department;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.dao
 * hyp create at 19-12-26
 **/
public interface DepartmentMapper {

    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);
}
