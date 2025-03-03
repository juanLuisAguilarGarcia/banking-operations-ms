package com.pichincha.infra.adapter.db.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.AccountType;
import com.pichincha.infra.adapter.db.entites.AccountsDto;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MapperAccountEntity {

    static Account toAccount(AccountsDto account){
        if (Objects.isNull(account)) {
            return Account.builder().build();
        }

        AccountType accountType = AccountType.builder().build();

        if (!Objects.isNull(account.getAccountType())){
            accountType = accountType.builder()
                    .accountTypeId(account.getAccountType().getAccountTypeId())
                    .description(account.getAccountType().getDescription()).build();

        }

        return Account.builder()
                .accountId(account.getAccountId())
                .isActive(account.getIsActive())
                .clientId(account.getClientId())
                .accountNumber(account.getAccountNumber())
                .initAmount(account.getInitAmount())
                .createAt(account.getCreateAt())
                .updateAt(account.getUpdateAt())
                .accountType(accountType).build();
    }
}
