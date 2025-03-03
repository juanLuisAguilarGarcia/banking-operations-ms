package com.pichincha.app.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.infra.adapter.db.entites.AccountTypeDto;
import com.pichincha.infra.adapter.db.entites.AccountsDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountsDataDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface AccountMapper {

     static AccountsDataDto toDto(Account account){
        AccountsDataDto accountsDataDto = AccountsDataDto.builder()
                .clientId(account.getClientId())
                .accountId(account.getAccountId())
                .accountNumber(account.getAccountNumber())
                .createAt(account.getCreateAt())
                .updateAt(account.getUpdateAt())
                .initAmount(account.getInitAmount())
                .isActive(account.getIsActive()).build();

        if(!Objects.isNull(account.getAccountType())){
            accountsDataDto.setAccountType(com.pichincha.infra.api.router.controller.dto.response.account.AccountTypeDto.builder()
                    .accountTypeId(account.getAccountType().getAccountTypeId())
                    .description(account.getAccountType().getDescription()).build());
        }

        return accountsDataDto;
    }

    static AccountDto toAccountDto(AccountsDataDto accountData, String msg){
        AccountDto accountDto =  AccountDto.builder().data(accountData).build();
        accountDto.setCode(String.valueOf(HttpStatus.OK.value()));
        accountDto.setMessage(msg);

        return accountDto;
    }

    static AccountsDto toAccountEntityDto(Account account) {
        AccountTypeDto accountTypeDto = new AccountTypeDto();

        if (Objects.isNull(account) ){
            return AccountsDto.builder().build();
        }

        if(!Objects.isNull(account.getAccountType())) {
            accountTypeDto = AccountTypeDto.builder()
                    .accountTypeId(account.getAccountType().getAccountTypeId())
                    .description(account.getAccountType().getDescription()).build();
        }

        return  AccountsDto.builder()
                .accountId(account.getAccountId())
                .clientId(account.getClientId())
                .isActive(true)
                .initAmount(account.getInitAmount())
                .accountNumber(account.getAccountNumber())
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .accountType(accountTypeDto).build();
    }
}
