package com.fpt.godii.application.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static com.fpt.godii.application.utils.constants.Constants.COMMON.TIME_DEFAULT_FORMAT;
import static com.fpt.godii.application.utils.constants.Constants.COMMON.UTC_TIMEZONE;

@Configuration
public class JsonConfig {
    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        DateFormat dateFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(UTC_TIMEZONE));
        objectMapper.setDateFormat(dateFormat);
    }
}
