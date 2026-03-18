package com.fpt.godii.application.advices;

import com.fpt.godii.application.advices.interceptors.RequestTimeLoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final RequestTimeLoggingInterceptor requestTimeLoggingInterceptor;

    public WebConfig(RequestTimeLoggingInterceptor requestTimeLoggingInterceptor) {
        super();
        this.requestTimeLoggingInterceptor = requestTimeLoggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestTimeLoggingInterceptor)
                .order(1);
    }
}
