package com.fpt.godii.application.domain.account.model.request;

import com.fpt.godii.application.base.request.PaginationParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetAllAccountRequest extends PaginationParams {
    private String Search;
}
