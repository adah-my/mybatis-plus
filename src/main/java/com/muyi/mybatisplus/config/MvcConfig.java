package com.muyi.mybatisplus.config;

import com.muyi.mybatisplus.common.BizInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 设置Interceptor不拦截Swagger
 * 如果项目设置了拦截器，会把Swagger拦截，所以需要设置通行
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer
{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BizInterceptor()).addPathPatterns("/**")
                // 多加一个路径排除
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/doc.html/**");;
    }
}