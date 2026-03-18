package com.fpt.godii.application.controller.base;

import com.fpt.godii.application.base.model.BaseResponse;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<BaseResponse<Void>> success() {
        return ResponseEntity.ok(BaseResponse.success());
    }

    public <T> ResponseEntity<BaseResponse<T>> success(T data) {
        return ResponseEntity.ok(BaseResponse.success(data));
    }
}
