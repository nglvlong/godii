package com.fpt.godii.application.domain.account.mapper;

import com.fpt.godii.application.base.mapper.IObjectMapper;
import com.fpt.godii.application.domain.account.model.entity.Account;
import com.fpt.godii.application.domain.account.model.request.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface IAccountRequestMapper extends IObjectMapper<Account, AccountRequest> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    Account merge(@MappingTarget Account target, AccountRequest source);

}
