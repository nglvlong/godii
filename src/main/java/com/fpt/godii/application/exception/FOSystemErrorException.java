package com.fpt.godii.application.exception;

import org.springframework.http.HttpStatus;

public class FOSystemErrorException extends BusinessException {

    public FOSystemErrorException(String messageCode) {
        this.setResponseStatus(new ResponseStatus(messageCode, messageCode, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
