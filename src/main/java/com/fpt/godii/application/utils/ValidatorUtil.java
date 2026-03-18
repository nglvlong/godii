package com.fpt.godii.application.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ValidatorUtil {
    Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    private final Validator validator;

    public ValidatorUtil(Validator validator) {
        this.validator = validator;
    }

    public <T> boolean selfValidate(T object){
        DataBinder dataBinder = new DataBinder(object);
        dataBinder.setValidator(validator);
        dataBinder.validate();
        dataBinder.getBindingResult().getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
        return dataBinder.getBindingResult().getAllErrors().isEmpty();
    }

    public <T> boolean selfValidateList(List<T> objects){
        for (T item : objects )
        {
            if(!selfValidate(item))
                return false;
        }
        return true;
    }
}
