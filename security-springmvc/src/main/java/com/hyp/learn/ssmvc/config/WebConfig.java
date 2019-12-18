package com.hyp.learn.ssmvc.config;

import com.hyp.learn.ssmvc.interceptor.SimpleAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author hyp
 * Project name is javaframework
 * Include in com.hyp.learn.ssmvc.config
 * hyp create at 19-12-17
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.hyp.learn.ssmvc"}, useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})}
)
public class WebConfig implements WebMvcConfigurer {
    //定制

    //视图解析器方案一
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有的页面都从 /WEB-INF/ xxx .jsp
        //registry.jsp();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

//    //视图解析器方案二
//    @Bean
//    public InternalResourceViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/views/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }


    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


//    //拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //super.addInterceptors(registry);
//        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
//    }

    //添加视图路由
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Autowired
    private SimpleAuthenticationInterceptor simpleAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(simpleAuthenticationInterceptor).addPathPatterns("/r/**");
    }

}
