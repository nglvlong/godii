package com.fpt.godii.application.domain.account.service.impl;

import com.fpt.godii.application.domain.account.mapper.IAccountRequestMapper;
import com.fpt.godii.application.domain.account.mapper.IAccountResponseMapper;
import com.fpt.godii.application.domain.account.model.entity.Account;
import com.fpt.godii.application.domain.account.model.request.AccountRequest;
import com.fpt.godii.application.domain.account.model.response.AccountResponse;
import com.fpt.godii.application.domain.account.repository.AccountRepository;
import com.fpt.godii.application.domain.account.service.AccountService;
import com.fpt.godii.application.exception.AccountCode;
import com.fpt.godii.application.exception.FONotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final IAccountRequestMapper accountRequestMapper;
    private final IAccountResponseMapper accountResponseMapper;

    @Override
    public List<AccountResponse> getAll() {

        List<Account> accounts = accountRepository.findAll();

        return accountResponseMapper.from(accounts);
    }

    @Override
    public AccountResponse getById(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new FONotFoundException(AccountCode.ACCOUNT_NOT_FOUND));

        return accountResponseMapper.from(account);
    }

    @Override
    public Account createIfNotExist(AccountRequest request) {

        Optional<Account> existed = accountRepository.findByEmail(request.getEmail());

        if (existed.isPresent()) {
            throw new FONotFoundException(AccountCode.EMAIL_ALREADY_EXISTS);
        }

        Account account = accountRequestMapper.to(request);
        account.setStatus(1);

        return accountRepository.save(account);
    }

    @Override
    public Boolean update(AccountRequest request) {

        Optional<Account> existed = accountRepository.findByEmail(request.getEmail());

        if (existed.isPresent()) {
            throw new FONotFoundException(AccountCode.EMAIL_ALREADY_EXISTS);
        }

        Account account = accountRepository.findById(request.getId()).orElseThrow(() -> new FONotFoundException(AccountCode.ACCOUNT_NOT_FOUND));

        Account save = accountRequestMapper.merge(account, request);

        accountRepository.save(save);

        return true;

    }

    @Override
    public Boolean changePassword(AccountRequest request) {

        Account account = accountRepository.findById(request.getId()).orElseThrow(() -> new FONotFoundException(AccountCode.ACCOUNT_NOT_FOUND));

        account.setPassword(request.getPassword());

        accountRepository.save(account);

        return true;
    }

    @Override
    public Boolean updateStatus(AccountRequest request) {

        Account account = accountRepository.findById(request.getId()).orElseThrow(() -> new FONotFoundException(AccountCode.ACCOUNT_NOT_FOUND));

        account.setStatus(request.getStatus());

        accountRepository.save(account);

        return true;
    }
}
