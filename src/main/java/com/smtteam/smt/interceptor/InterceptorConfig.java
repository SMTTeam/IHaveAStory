package com.smtteam.smt.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Autowired
    private LoginInterceptor loginInterceptor ;

    /**
      *注册（自定义）拦截器
      */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登录与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/error","/register","/invite/accept/**","/register/**","/checklogin","/css/**","/img/**","/vendor/**","/js/**");


//        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
//        registration.addPathPatterns("/**");
//        registration.excludePathPatterns("/login","/register","/register/*","/checklogin");
    }

    /**
     *  配置静态资源，比如html,css,js等
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

//        super.addResourceHandlers(registry);
    }
}
