package com.fpt.godii.application.controller;

import com.fpt.godii.application.base.model.BaseResponse;
import com.fpt.godii.application.controller.base.BaseController;
import com.fpt.godii.application.domain.account.model.entity.Account;
import com.fpt.godii.application.domain.account.model.request.AccountRequest;
import com.fpt.godii.application.domain.account.model.response.AccountResponse;
import com.fpt.godii.application.domain.account.service.AccountService;
import com.fpt.godii.application.validators.AccountValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Slf4j
public class AccountController extends BaseController {

    private final AccountService accountService;

    private final AccountValidator accountValidator;


    @GetMapping("/all")
    public BaseResponse<List<AccountResponse>> getAll() {
        log.info("Get all account");

        List<AccountResponse> responses = accountService.getAll();

        return BaseResponse.success(responses);
    }

    @GetMapping("/getById")
    public BaseResponse<AccountResponse> getById(@RequestParam Long id) {
        log.info("Get account by Id");

        AccountResponse responses = accountService.getById(id);

        return BaseResponse.success(responses);
    }

    @PostMapping("/create")
    public BaseResponse<Account> createIfNotExist(@RequestBody AccountRequest request) {
        log.info("Create account if not exist");

        accountValidator.validateCreateAccountRequest(request);

        Account response = accountService.createIfNotExist(request);

        return BaseResponse.success(response);
    }

    @PostMapping("/changePassword")
    public BaseResponse<Boolean> changePassword(@RequestBody AccountRequest request) {
        log.info("Change account password");

        accountValidator.validateUpdatePasswordRequest(request);

        Boolean response = accountService.changePassword(request);

        return BaseResponse.success(response);
    }

    @PostMapping("/updateStatus")
    public BaseResponse<Boolean> updateStatus(@RequestBody AccountRequest request) {
        log.info("Modify account status");

        accountValidator.validateUpdateStatusRequest(request);

        Boolean response = accountService.updateStatus(request);

        return BaseResponse.success(response);
    }

    @PostMapping("/updateAccount")
    public BaseResponse<Boolean> updateAccount(@RequestBody AccountRequest request) {
        log.info("Update account status");

        accountValidator.validateUpdateAccountRequest(request);

        Boolean response = accountService.update(request);

        return BaseResponse.success(response);
    }
}
