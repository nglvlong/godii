package com.fpt.godii.application.domain.account.mapper;

import com.fpt.godii.application.base.mapper.IObjectMapper;
import com.fpt.godii.application.domain.account.model.entity.Account;
import com.fpt.godii.application.domain.account.model.response.AccountResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IAccountResponseMapper extends IObjectMapper<Account, AccountResponse> {

}
