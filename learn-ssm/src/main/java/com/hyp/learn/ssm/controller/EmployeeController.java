package com.hyp.learn.ssm.controller;

import com.hyp.learn.ssm.pojo.Employee;
import com.hyp.learn.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssm.controller
 * hyp create at 19-12-27
 **/
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/getemps", method = RequestMethod.GET)
    public String emps(Map<String, Object> map) {
        List<Employee> emps = employeeService.getEmps();
        map.put("allEmps", emps);
        return "list";
    }

}
