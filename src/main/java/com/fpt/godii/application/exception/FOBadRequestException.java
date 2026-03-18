package com.fpt.godii.application.exception;

import org.springframework.http.HttpStatus;

public class FOBadRequestException extends BusinessException {

    public FOBadRequestException(String messageCode) {
        this.setResponseStatus(new ResponseStatus(messageCode, messageCode, HttpStatus.BAD_REQUEST));
    }
}
