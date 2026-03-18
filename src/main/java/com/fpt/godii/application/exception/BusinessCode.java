package com.fpt.godii.application.exception;

import org.springframework.http.HttpStatus;


public class BusinessCode {
    public static final ResponseStatus SUCCESS =
            new ResponseStatus("SUCCESS", "success", HttpStatus.OK);

    public static final ResponseStatus INTERNAL_SERVER_ERROR =
            new ResponseStatus("INTERNAL_SERVER_ERROR", "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    public static final ResponseStatus BAD_REQUEST = new ResponseStatus("BAD_REQUEST",
            "Bad Request", HttpStatus.BAD_REQUEST);

    public static final ResponseStatus NOT_FOUND =
            new ResponseStatus("NOT_FOUND", "Not found", HttpStatus.NOT_FOUND);

}
