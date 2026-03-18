package com.fpt.godii.application.validators;

import com.fpt.godii.application.domain.account.model.request.AccountRequest;

import com.fpt.godii.application.exception.AccountCode;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator {

    public void validateCreateAccountRequest(AccountRequest request) {
        Validator.of(request)
                .requireNonBlank(AccountRequest::getUsername, AccountCode.USERNAME_BLANK)
                .requireNonBlank(AccountRequest::getPassword, AccountCode.PASSWORD_BLANK)
                .requireNonBlank(AccountRequest::getEmail, AccountCode.EMAIL_BLANK)
                .requireValidEmail(AccountRequest::getEmail, AccountCode.EMAIL_INVALID)
                .validate(AccountRequest::getPassword, password -> password.length() > 255, AccountCode.PASSWORD_MAX_LENGTH)
                .validate(AccountRequest::getPassword, password -> password.length() < 8, AccountCode.PASSWORD_MIN_LENGTH)
                .get();
    }

    public void validateUpdatePasswordRequest(AccountRequest request) {
        Validator.of(request)
                .requireNonBlank(AccountRequest::getPassword, AccountCode.PASSWORD_BLANK)
                .validate(AccountRequest::getPassword, password -> password.length() > 255, AccountCode.PASSWORD_MAX_LENGTH)
                .validate(AccountRequest::getPassword, password -> password.length() < 8, AccountCode.PASSWORD_MIN_LENGTH)
                .get();
    }

    public void validateUpdateStatusRequest(AccountRequest request) {
        Validator.of(request)
                .validate(AccountRequest::getStatus, status -> status > 1, AccountCode.STATUS_MAX_LENGTH)
                .validate(AccountRequest::getStatus, status -> status < 0, AccountCode.STATUS_MIN_LENGTH)
                .get();
    }

    public void validateUpdateAccountRequest(AccountRequest request) {
        Validator.of(request)
                .requireValidEmail(AccountRequest::getEmail, AccountCode.EMAIL_INVALID)
                .validate(AccountRequest::getStatus, status -> status > 1, AccountCode.STATUS_MAX_LENGTH)
                .validate(AccountRequest::getStatus, status -> status < 0, AccountCode.STATUS_MIN_LENGTH)
                .get();
    }
}
