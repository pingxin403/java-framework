package com.hyp.learn;

import com.hyp.learn.config.AppMvcConfig;
import com.hyp.learn.config.AppRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web容器启动的时候创建对象；调用方法来初始化容器以前前端控制器
//spring web包下META-INF/services/javax.servlet.ServletContainerInitializer文件中包含：
//org.springframework.web.SpringServletContainerInitializer

/**
 * SpringServletContainerInitializer这个类上的注释@HandlesTypes(WebApplicationInitializer.class)，服务器启动时，spring会通过这个类中的onStartup（）方法加载WebApplicationInitializer这个接口及其子类，那么我们只需要写一个类实现这个接口，再把我们需要启动时加载的部分放在这个类中即可。
 * <p>
 * AbstractAnnotationConfigDispatcherServletInitializer 是WebApplicationInitializer接口的一个实现类
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //获取根容器的配置类；（Spring的配置文件）   父容器；
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return new Class<?>[]{AppRootConfig.class};
    }

    //获取web容器的配置类（SpringMVC配置文件）  子容器；
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return new Class<?>[]{AppMvcConfig.class};
    }

    //获取DispatcherServlet的映射信息
    //  /：拦截所有请求（包括静态资源（xx.js,xx.png）），但是不包括*.jsp；
    //  /*：拦截所有请求；连*.jsp页面都拦截；jsp页面是tomcat的jsp引擎解析的；
    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return new String[]{"/"};
    }

}
//类似以下web.xml配置
//<web‐app>
//<listener>
//<listener‐class>org.springframework.web.context.ContextLoaderListener</listener‐class>
//</listener>
//<context‐param>
//<param‐name>contextConfigLocation</param‐name>
//<param‐value>/WEB‐INF/application‐context.xml</param‐value>
//</context‐param>
//<servlet>
//<servlet‐name>springmvc</servlet‐name>
//<servlet‐class>org.springframework.web.servlet.DispatcherServlet</servlet‐class>
//<init‐param>
//<param‐name>contextConfigLocation</param‐name>
//<param‐value>/WEB‐INF/spring‐dispatcher-servlet.xml</param‐value>
//</init‐param>
//<load‐on‐startup>1</load‐on‐startup>
//</servlet>
//<servlet‐mapping>
//<servlet‐name>springmvc</servlet‐name>
//<url‐pattern>/</url‐pattern>
//</servlet‐mapping>
//</web‐app>