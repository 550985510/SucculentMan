package com.tangdou.succulent.manager.config;

import com.tangdou.succulent.manager.bean.staff.StaffUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 木叶丸
 * Created by 木叶丸 on 2018/2/13 18:54
 */
@Configuration
public class AdminSecurityConfig extends WebMvcConfigurerAdapter {

    @Value("${server.context-path}")
    private String requestPath;

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error_403");
        addInterceptor.excludePathPatterns("/error_404");
        addInterceptor.excludePathPatterns("/error_500");
        addInterceptor.excludePathPatterns("/api/staff/login");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/hessian/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            StaffUser staffUser = (StaffUser) session.getAttribute(SESSION_KEY);
            if (staffUser != null) {
                Integer roleId = staffUser.getRoleId();
                String requestUri = request.getRequestURI();
                String contextPath = request.getContextPath();
                String url = requestUri.substring(contextPath.length());
                //logger.info("url:"+url);
                if (roleId != 1 && ("/staff/user".equals(url) || "/staff/dept".equals(url) || "/staff/role".equals(url))) {
                    response.sendRedirect(requestPath + "/error_403");
                    return false;
                } else if(roleId == 3 && ("/module/list".equals(url) || "/user/list".equals(url) || "/article/list".equals(url) || "/article/banner/list".equals(url))) {
                    response.sendRedirect(requestPath + "/error_403");
                    return false;
                } else {
                    return true;
                }
            }

            // 跳转登录
            response.sendRedirect(requestPath + "/login");
            return false;
        }
    }
}
