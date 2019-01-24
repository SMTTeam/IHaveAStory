package com.smtteam.smt.interceptor;

import com.smtteam.smt.common.bean.ShowUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**

     * controller 执行之前调用

     */
    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        String loginfo = request.getRequestURI()+"   , -------------";
        logger.info(loginfo);
        //从Session里面获取User user引用判断是否为空 来实现登录拦截逻辑
        HttpSession session = request.getSession();

        ShowUser showUser = (ShowUser) session.getAttribute("user");

        if ( showUser == null){

            logger.info("您尚未登录!");
            response.sendRedirect("/login");//让用户进行登录
            return false;
        }else {
            return true;
        }
    }

    /**

     * controller 执行之后，且页面渲染之前调用

     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView)throws Exception{
        //如果需要在controller执行后调用，需要编写相关代码
    }

    /**

     * 页面渲染之后调用，一般用于资源清理操作

     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex)throws Exception{
        //如果需要在页面渲染之后，进行资源清理操作，需要编写相关代码
    }
}
