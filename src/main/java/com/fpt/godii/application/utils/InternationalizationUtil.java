package com.fpt.godii.application.utils;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class InternationalizationUtil {

    private final MessageSource messageSource;

    private final LocaleResolver localeResolver;

    public String getInternationalizationMessage(String key) {
        try {
            RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) reqAttr;
            if (servletRequestAttributes == null) {
                return messageSource.getMessage(key, null, Locale.getDefault());
            }

            HttpServletRequest req = servletRequestAttributes.getRequest();
            return messageSource.getMessage(key, null, localeResolver.resolveLocale(req));
        } catch (Exception ex) {
            return key;
        }
    }
}