package com.fpt.godii.application.exception;

import org.springframework.http.HttpStatus;

public class FONotFoundException extends BusinessException {

    public FONotFoundException(String messageCode) {
        this.setResponseStatus(new ResponseStatus(messageCode, messageCode, HttpStatus.NOT_FOUND));
    }
}
