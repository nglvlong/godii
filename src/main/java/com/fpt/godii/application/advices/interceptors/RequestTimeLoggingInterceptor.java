package com.fpt.godii.application.advices.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Component
public class RequestTimeLoggingInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(RequestTimeLoggingInterceptor.class);

    @Value("${tdx.performance.expenditure.api_time_request_limit:500}")
    private long apiTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var requestId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        request.setAttribute("requestId", requestId);
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if(executeTime > apiTime)
            logger.warn("requestId {}, Handle :{} , request take time: {}",request.getAttribute("requestId"), handler, executeTime);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
