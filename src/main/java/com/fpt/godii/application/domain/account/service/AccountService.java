package com.fpt.godii.application.domain.account.service;

import com.fpt.godii.application.domain.account.model.entity.Account;
import com.fpt.godii.application.domain.account.model.request.AccountRequest;
import com.fpt.godii.application.domain.account.model.response.AccountResponse;

import java.util.List;

public interface AccountService {

    List<AccountResponse> getAll();

    AccountResponse getById(Long id);

    Account createIfNotExist(AccountRequest request);

    Boolean update(AccountRequest request);

    Boolean changePassword(AccountRequest request);

    Boolean updateStatus(AccountRequest request);

}
