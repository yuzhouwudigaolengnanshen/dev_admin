package com.dev.admin.web.interceptor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.http.HttpUtil;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.entity.Log;
import com.dev.admin.entity.User;
import com.dev.admin.service.LogService;
import com.dev.admin.web.annotation.MenuName;
import com.dev.common.util.Constant;
import com.dev.common.util.UserCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 */
public class LogInterceptor extends HandlerInterceptorAdapter{

    private final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    @Reference
    private LogService logService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTimes",System.currentTimeMillis());
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }
    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        //User user = userCache.get();
              User user    =  (User) redisTemplate.opsForValue().get(Constant.cacheName);
        Log log = new Log();
        log.setCreateDatetime(DateUtil.date());
        log.setRequestPath(request.getServletPath());
        log.setUserId(user.getId());
        log.setUserName(user.getName());
        if(ex != null){
            log.setIsError(1);
            log.setError(ExceptionUtil.stacktraceToString(ex));
        }
        log.setIpAddress(HttpUtil.getClientIP(request));

        Object startTimesValue = request.getAttribute("startTimes");
        if(startTimesValue != null){
            long endTimes = System.currentTimeMillis();
            log.setCost(endTimes - Long.parseLong(startTimesValue.toString()));
        }

        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Class<?> type = handlerMethod.getBeanType();
            MenuName menuName = type.getAnnotation(MenuName.class);
            if(menuName != null){
                log.setMenuName(menuName.value());
            }else{
                String beanTypeName = handlerMethod.getBeanType().getSimpleName();
                log.setMenuName(beanTypeName);
            }
        }

        logService.addLog(log);
    }

}