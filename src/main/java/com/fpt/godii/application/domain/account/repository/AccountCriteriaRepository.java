package com.fpt.godii.application.domain.account.repository;

import com.fpt.godii.application.base.response.PaginationDTO;
import com.fpt.godii.application.domain.account.model.entity.Account;
import com.fpt.godii.application.domain.account.model.request.GetAllAccountRequest;

public interface AccountCriteriaRepository {
    PaginationDTO<Account> getAll(GetAllAccountRequest getAllAccountRequest);
}
