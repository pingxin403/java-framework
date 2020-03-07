package com.hyp.learn.ssm.dao;

import com.hyp.learn.ssm.pojo.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.dao
 * hyp create at 19-12-26
 **/
public interface EmployeeMapper {

    //多条记录封装一个map：Map<Integer,Employee>:键是这条记录的主键，值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("lastName")
    public Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map；key就是列名，值就是对应的值
    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Employee getEmpByMap(Map<String, Object> map);

    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmpById(Integer id);

    public Long addEmp(Employee employee);

    public boolean updateEmp(Employee employee);

    public boolean deleteEmpById(Integer id);

    public List<Employee> getEmps();
}
