package com.leyou.cart.interceptor;

import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.leyou.cart.config.JwtProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableConfigurationProperties(JwtProperties.class)
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtProperties jwtProperties;
    // 定义一个线程域(线程局部变量)，存放登录用户
    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    public LoginInterceptor(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取cookie中的token
        String token = CookieUtils.getCookieValue(request, this.jwtProperties.getCookieName());
        if (StringUtils.isBlank(token)) {
            // 未登录,返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        // 有token，查询用户信息
        try {
            // 解析成功，证明已经登录
            UserInfo user = JwtUtils.getInfoFromToken(token, this.jwtProperties.getPublicKey());
            // 放入线程域
            THREAD_LOCAL.set(user);
            return true;
        } catch (Exception e) {
            // 抛出异常，证明未登录,返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空线程的局部变量。因为使用的是tomcat的线程池，线程不会结束，也就不会释放线程的局部变量。
        THREAD_LOCAL.remove();
    }

    public static UserInfo getLoginUser() {
        return THREAD_LOCAL.get();
    }
}
