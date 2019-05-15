package com.dev.admin.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DevWebConfiguraction implements WebMvcConfigurer {

    //配置拦截器
    /*public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor此方法添加拦截器
        registry.addInterceptor(logInterceptor()).addPathPatterns("/login/**","/doLogin/**","/doLogout/**","/menu/**","/user/**","/role/**","/org/**");
    }

    @Bean
    LogInterceptor logInterceptor(){
        return new LogInterceptor();
    }
*/

    /*@Bean
    public FilterRegistrationBean shiroAuthcFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new ShiroAuthcFilter());
        registration.addUrlPatterns("/user/**"); //
        registration.addUrlPatterns("/org/**"); //
        registration.addUrlPatterns("/role/**"); //
        registration.addUrlPatterns("/menu/**"); //
        registration.addUrlPatterns("/log/**"); //
        registration.addUrlPatterns("/index"); //
        return registration;
    }*/

}