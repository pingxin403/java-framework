package com.hyp.learn.mybatis.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.mybatis.pojo
 * hyp create at 19-12-22
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    private List<Orders> ordersList; //用户关联的订单




}
