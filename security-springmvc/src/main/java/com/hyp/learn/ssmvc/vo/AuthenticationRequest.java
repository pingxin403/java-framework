package com.hyp.learn.ssmvc.vo;

import lombok.Data;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.vo
 * hyp create at 19-12-18
 **/
@Data
public class AuthenticationRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
