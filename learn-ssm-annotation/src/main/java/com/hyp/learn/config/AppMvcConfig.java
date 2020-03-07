package com.hyp.learn.config;


import com.hyp.learn.controller.MyFirstInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 这里首先同样表明它是一个配置类，然后@ComponentScan("com.hyp.learn")表示扫描这个包下的所有组件，即包含有类似@Controller，@Configuration，@Bean等注解的部分。@EnaleWebMvc表示启用WebMvc的相关注解和默认配置。这个类继承了WebMvcConfigurerAdapter，从名字可以看出这是一个关于WebMvc配置的类，可以通过重写父类方法的方式为当前项目进行配置。类中加载了一个视图解析器，视图文件的路径是/WEB-INF/pages，文件的类型是.html文件。此外这个类中还可以通过重写addInterceptors（）方法添加拦截器。
 *
 */

//SpringMVC只扫描Controller；子容器
//useDefaultFilters=false 禁用默认的过滤规则；
@ComponentScan(value = "com.hyp.learn", includeFilters = {
        @Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
}, useDefaultFilters = false)
@EnableWebMvc
@Configuration
//spring 5.0 以后WebMvcConfigurerAdapter已经过时，因为接口已经拥有default方法。
public class AppMvcConfig implements WebMvcConfigurer {

    //定制

    //视图解析器方案一
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有的页面都从 /WEB-INF/ xxx .jsp
        //registry.jsp();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    //	//视图解析器方案二
//	@Bean
//	public InternalResourceViewResolver viewResolver(){
//		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}
    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // TODO Auto-generated method stub
        configurer.enable();
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        //super.addInterceptors(registry);
        registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
    }

//	//添加视图路由
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("login");
//	}

}