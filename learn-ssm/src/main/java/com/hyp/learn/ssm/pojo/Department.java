package com.hyp.learn.ssm.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.pojo
 * hyp create at 19-12-26
 **/
public class Department implements Serializable {

    private Integer id;
    private String departmentName;
    private List<Employee> emps;

    public Department(int i) {
        id=i;
    }

    public Department() {
    }

    public List<Employee> getEmps() {
        return emps;
    }
    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    @Override
    public String toString() {
        return "Department [id=" + id + ", departmentName=" + departmentName
                + "]";
    }



}
