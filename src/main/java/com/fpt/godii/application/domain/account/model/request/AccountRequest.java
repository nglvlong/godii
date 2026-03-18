package com.fpt.godii.application.domain.account.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequest {

    Long id;

    String username;

    String email;

    String password;

    Integer status;

    String type;

}
