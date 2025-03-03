package com.pichincha.app.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.adapter.db.entites.AccountTypeDto;
import com.pichincha.infra.adapter.db.entites.AccountsDto;
import com.pichincha.infra.adapter.db.entites.MovementsDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountsDataDto;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDataDto;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    static MovementDataDto toDto(Movement movement){

        return MovementDataDto.builder()
                .movementId(movement.getMovementId())
                .accountId(movement.getAccountId())
                .movementDate(movement.getMovementDate())
                .amount(movement.getAmount())
                .balanceAmount(movement.getBalanceAmount())
                .createAt(movement.getCreateAt())
                .updateAt(movement.getUpdateAt()).build();
    }

    static MovementDto toMovementDto(MovementDataDto movementData, String msg){
        MovementDto movementDto =  MovementDto.builder().data(movementData).build();
        movementDto.setCode(String.valueOf(HttpStatus.OK.value()));
        movementDto.setMessage(msg);

        return movementDto;
    }

    static MovementsDto toMovementEntityDto(Movement movement) {

        if (Objects.isNull(movement) ){
            return MovementsDto.builder().build();
        }

        return  MovementsDto.builder()
                .account(AccountsDto.builder().accountId(movement.getAccountId()).build())
                .amount(movement.getAmount())
                .movementId(movement.getMovementId())
                .balanceAmount(movement.getBalanceAmount())
                .movementDate(movement.getMovementDate())
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now())).build();
    }
}
