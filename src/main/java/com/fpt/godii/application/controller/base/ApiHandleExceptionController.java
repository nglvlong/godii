package com.fpt.godii.application.controller.base;


import com.fpt.godii.application.base.model.BaseResponse;
import com.fpt.godii.application.exception.BusinessException;
import com.fpt.godii.application.exception.FONotFoundException;
import com.fpt.godii.application.exception.ResponseStatus;
import com.fpt.godii.application.exception.FOBadRequestException;
import com.fpt.godii.application.utils.InternationalizationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiHandleExceptionController {

    private final InternationalizationUtil internationalizationUtil;

    @ExceptionHandler(FOBadRequestException.class)
    public ResponseEntity<BaseResponse<ResponseStatus>> handleValidateError(FOBadRequestException validateException) {
        updateMessage(validateException);
        return new ResponseEntity<>(BaseResponse.fail(validateException), validateException.getResponseStatus().getStatus());
    }

    @ExceptionHandler(FONotFoundException.class)
    public ResponseEntity<BaseResponse<ResponseStatus>> handleSystemError(FONotFoundException businessException) {
        updateMessage(businessException);
        return new ResponseEntity<>(BaseResponse.fail(businessException), businessException.getResponseStatus().getStatus());
    }

    private void updateMessage(BusinessException businessException){
        var message = internationalizationUtil.getInternationalizationMessage(businessException.getResponseStatus().getCode());
        businessException.getResponseStatus().setMessage(message);
    }

}
