package com.hyp.learn.ssmvc.controller;

import com.hyp.learn.ssmvc.commons.SessionKeys;
import com.hyp.learn.ssmvc.dto.UserDto;
import com.hyp.learn.ssmvc.service.AuthenticationService;
import com.hyp.learn.ssmvc.vo.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.controller
 * hyp create at 19-12-18
 **/
@RestController
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 用户登录
     *
     * @param authenticationRequest 登录请求
     * @return
     */
    @PostMapping(value = "/login", produces = {"text/plain;charset=utf8"})
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        UserDto userDetails = authenticationService.authentication(authenticationRequest);
        session.setAttribute(SessionKeys.SESSION_USER_KEY, userDetails);
        return userDetails.getFullname() + " 登录成功";
    }

    @GetMapping(value = "/logout", produces = "text/plain;charset=utf8")
    public String logout(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute(SessionKeys.SESSION_USER_KEY);
        if (Objects.isNull(user) ||
                StringUtils.isEmpty(user.getFullname())
                || StringUtils.isEmpty(user.getPassword())) {
            return "用户未登录";
        }

        session.removeAttribute(SessionKeys.SESSION_USER_KEY);
        return "退出成功";
    }

    /**
     * 测试资源1
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object userObj = session.getAttribute(SessionKeys.SESSION_USER_KEY);
        if (userObj != null) {
            fullname = ((UserDto) userObj).getFullname();
        } else {
            fullname = "匿名";
        }
        return fullname + " 访问资源1";
    }

    /**
     * 测试资源2
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r2", produces = {"text/plain;charset=utf8"})
    public String r2(HttpSession session) {
        String fullname = null;
        Object userObj = session.getAttribute(SessionKeys.SESSION_USER_KEY);
        if (userObj != null) {
            fullname = ((UserDto) userObj).getFullname();
        } else {
            fullname = "匿名";
        }
        return fullname + " 访问资源2";
    }
}
