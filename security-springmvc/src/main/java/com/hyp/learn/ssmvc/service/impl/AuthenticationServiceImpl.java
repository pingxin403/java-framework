package com.hyp.learn.ssmvc.service.impl;

import com.hyp.learn.ssmvc.dto.UserDto;
import com.hyp.learn.ssmvc.service.AuthenticationService;
import com.hyp.learn.ssmvc.vo.AuthenticationRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.service.impl
 * hyp create at 19-12-18
 **/
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (Objects.isNull(authenticationRequest) ||
                StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("账号或者密码为空");

        }
        UserDto userDto = getUserDto(authenticationRequest.getUsername());

        if (Objects.isNull(userDto)) {
            throw new RuntimeException("查询不到该用户");
        }

        if (!authenticationRequest.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("账号或者密码错误");
        }


        return userDto;
    }

    //模拟用户查询
    public UserDto getUserDto(String username) {
        return userMap.get(username);
    }

    //    //用户信息
//    private Map<String, UserDto> userMap = new HashMap<>();
//
//    {
//        userMap.put("zhangsan", new UserDto("1010", "zhangsan", "123", "张三", "133443"));
//        userMap.put("lisi", new UserDto("1011", "lisi", "456", "李四", "144553"));
//    }
    //用户信息
    private Map<String, UserDto> userMap = new HashMap<>();

    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");
        userMap.put("zhangsan", new UserDto("1010", "zhangsan", "123", "张 三", "133443", authorities1));
        userMap.put("lisi", new UserDto("1011", "lisi", "456", "李四", "144553", authorities2));
    }
}
