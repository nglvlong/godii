package com.fpt.godii.application.domain.account.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    Long id;
    String username;
    Integer status;
    String type;
    String email;
    Instant createdDate;
    Instant updatedDate;
}
