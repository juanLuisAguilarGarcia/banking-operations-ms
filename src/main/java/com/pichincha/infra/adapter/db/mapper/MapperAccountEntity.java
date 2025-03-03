package com.pichincha.infra.adapter.db.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.AccountType;
import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.adapter.db.entites.AccountsDto;
import com.pichincha.infra.adapter.db.entites.MovementsDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MapperAccountEntity {

    static Account toAccount(AccountsDto account){
        if (Objects.isNull(account)) {
            return Account.builder().build();
        }

        AccountType accountType = AccountType.builder().build();
        List<Movement> movements = new ArrayList<>();

        if (!Objects.isNull(account.getAccountType())){
            accountType = accountType.builder()
                    .accountTypeId(account.getAccountType().getAccountTypeId())
                    .description(account.getAccountType().getDescription()).build();

        }

        if (!account.getMovements().isEmpty()){
             account.getMovements().forEach(m -> movements.add(Movement.builder()
                            .updateAt(m.getUpdateAt())
                     .balanceAmount(m.getBalanceAmount())
                     .amount(m.getAmount())
                     .accountId(m.getAccount().getAccountId())
                     .createAt(m.getCreateAt())
                     .movementId(m.getMovementId())
                     .movementDate(m.getMovementDate())
                     .build()));
        }

        return Account.builder()
                .accountId(account.getAccountId())
                .isActive(account.getIsActive())
                .clientId(account.getClientId())
                .accountNumber(account.getAccountNumber())
                .initAmount(account.getInitAmount())
                .createAt(account.getCreateAt())
                .updateAt(account.getUpdateAt())
                .accountType(accountType)
                .movements(movements).build();
    }
}
