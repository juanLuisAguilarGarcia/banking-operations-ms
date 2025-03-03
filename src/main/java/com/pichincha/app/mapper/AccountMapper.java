package com.pichincha.app.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.adapter.db.entites.AccountTypeDto;
import com.pichincha.infra.adapter.db.entites.AccountsDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountStateDataDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountStateDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountsDataDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDataDto;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    static AccountStateDto toAccountStateDto(List<Account> accounts, String msg, String clientName){
        AccountStateDto accountStateDto =  AccountStateDto.builder().data(toAccountStateDataDtoLis(accounts, clientName)).build();
        accountStateDto.setCode(String.valueOf(HttpStatus.OK.value()));
        accountStateDto.setMessage(msg);

        return accountStateDto;
    }

    static List<AccountStateDataDto> toAccountStateDataDtoLis(List<Account> accounts, String clientName){

         if(accounts.isEmpty()){
             return new ArrayList<>();
         }

        List<AccountStateDataDto> accountStates = new ArrayList<>();

        accounts.forEach(a ->

                accountStates.add(AccountStateDataDto.builder()
                        .date(a.getCreateAt())
                        .accountNumber(a.getAccountNumber())
                        .clientName(clientName)
                        .initAmount(a.getInitAmount())
                        .isActive(a.getIsActive())
                        .balanceAmount((!a.getMovements().isEmpty() &&  a.getMovements().size() > 0)
                                ? a.getMovements().get(0).getBalanceAmount() : BigDecimal.ZERO)
                        .accountType(Objects.isNull(a.getAccountType()) ?
                                com.pichincha.infra.api.router.controller.dto.response.account.AccountTypeDto.builder().build() :
                                com.pichincha.infra.api.router.controller.dto.response.account.AccountTypeDto.builder()
                                        .description(a.getAccountType().getDescription())
                                        .accountTypeId(a.getAccountType().getAccountTypeId()).build())
                        .movements(toMovementDtoLis(a.getMovements())).build()));

        return accountStates;
    }

    static List<MovementDataDto> toMovementDtoLis(List<Movement> movements){
        if(movements.isEmpty()){
            return new ArrayList<>();
        }

        List<MovementDataDto> movementsDto = new ArrayList<>();

        movements.forEach(m -> movementsDto.add(MovementDataDto.builder()
                .balanceAmount(m.getBalanceAmount())
                .movementId(m.getMovementId())
                .movementDate(m.getMovementDate())
                .amount(m.getAmount())
                .createAt(m.getCreateAt())
                .updateAt(m.getUpdateAt())
                .accountId(m.getAccountId()).build()));

        return movementsDto;
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
                .movements(new ArrayList<>())
                .accountType(accountTypeDto).build();
    }
}
