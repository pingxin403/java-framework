package com.hyp.learn.ssm.service;

import com.hyp.learn.ssm.dao.EmployeeMapper;
import com.hyp.learn.ssm.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssm.service
 * hyp create at 19-12-27
 **/
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;

    public List<Employee> getEmps(){
        //
        //EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        return employeeMapper.getEmps();
    }

}