package com.pichincha.infra.api.router.controller.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.AccountType;
import com.pichincha.infra.api.router.controller.dto.request.CreateAccountDto;
import com.pichincha.infra.api.router.controller.dto.request.UpdateMovementDto;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface AccountsDtoMapper {

    static Account toEntity(CreateAccountDto createAccountDto, Long accountId){
        if(Objects.isNull(createAccountDto)){
            return Account.builder().build();
        }

        Account account = Account.builder()
                .accountId(accountId)
                .clientId(createAccountDto.getClientId())
                .accountNumber(createAccountDto.getAccountNumber())
                .accountType(AccountType.builder()
                        .accountTypeId(createAccountDto.getAccountTypeId()).build())
                .initAmount(createAccountDto.getInitAmount()).build();

        return account;
    }
}
