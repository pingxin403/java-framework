package com.hyp.learn.mybatis.dao;

import com.hyp.learn.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.dao
 * hyp create at 19-12-26
 **/
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id=#{id}")
    public Employee getEmpById(Integer id);
}
