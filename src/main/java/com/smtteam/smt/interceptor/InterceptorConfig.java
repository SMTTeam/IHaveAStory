package com.smtteam.smt.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
                .excludePathPatterns("/","/login","/register","/invite/accept/**","/register/**","/checklogin","/findbackpsw","/findpswResetPsw",
                        "/userinfo/sendresetpswemail","/userinfo/resetpsw/**","/userinfo/resetpassword" ,"/linkInvalid","/css/**","/img/**","/vendor/**","/js/**");
    }

    /**
     *  配置静态资源，比如html,css,js等
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // 如果要配置静态资源，可以在这里做
    }
}