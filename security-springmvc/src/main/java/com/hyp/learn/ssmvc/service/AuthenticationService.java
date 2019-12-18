package com.hyp.learn.ssmvc.service;

import com.hyp.learn.ssmvc.dto.UserDto;
import com.hyp.learn.ssmvc.vo.AuthenticationRequest;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc
 * hyp create at 19-12-18
 **/
public interface AuthenticationService {
    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求
     * @return 认证成功的用户信息
     */
    public UserDto authentication(AuthenticationRequest authenticationRequest);
}
