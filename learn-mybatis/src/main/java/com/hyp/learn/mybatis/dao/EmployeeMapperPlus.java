package com.hyp.learn.mybatis.dao;

import com.hyp.learn.mybatis.pojo.Employee;

import java.util.List;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.dao
 * hyp create at 19-12-26
 **/
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer deptId);

}
