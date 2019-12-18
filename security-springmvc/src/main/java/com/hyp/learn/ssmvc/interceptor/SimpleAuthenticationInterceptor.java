package com.hyp.learn.ssmvc.interceptor;

import com.hyp.learn.ssmvc.commons.SessionKeys;
import com.hyp.learn.ssmvc.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.interceptor
 * hyp create at 19-12-18
 **/
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    //请求拦截方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //读取会话信息
        Object object = request.getSession().getAttribute(SessionKeys.SESSION_USER_KEY);
        if (object == null) {
            writeContent(response, "请登录");
        }

        UserDto user = (UserDto) object;
        if (Objects.nonNull(user)) {
            //请求的url
            String requesturi = request.getRequestURI();
            if (user.getAuthorities().contains("p1") && requesturi.contains("/r1")) {
                return true;
            }
            if (user.getAuthorities().contains("p2") && requesturi.contains("/r2")) {
                return true;
            }
        }
        writeContent(response, "权限不足,拒绝访问");
        return false;
    }

    //响应输出
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
//        response.resetBuffer();
    }
}
