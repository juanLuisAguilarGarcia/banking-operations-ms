package com.pichincha.infra.api.router.controller.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.AccountType;
import com.pichincha.infra.api.router.controller.dto.request.CreateAccountDto;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface AccountsDtoMapper {

    static Account toEntity(CreateAccountDto createAccountDto){
        if(Objects.isNull(createAccountDto)){
            return Account.builder().build();
        }

        Account account = Account.builder()
                .clientId(createAccountDto.getClientId())
                .accountNumber(createAccountDto.getAccountNumber())
                .accountType(AccountType.builder()
                        .accountTypeId(createAccountDto.getAccountTypeId()).build())
                .initAmount(createAccountDto.getInitAmount()).build();

        return account;
    }
}
