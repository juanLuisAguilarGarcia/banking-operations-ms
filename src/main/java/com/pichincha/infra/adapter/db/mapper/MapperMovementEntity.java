package com.pichincha.infra.adapter.db.mapper;

import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.adapter.db.entites.MovementsDto;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MapperMovementEntity {

    static Movement toMovement(MovementsDto movement){
        if (Objects.isNull(movement)) {
            return Movement.builder().build();
        }

        return Movement.builder()
                .accountId(Objects.isNull(movement.getAccount()) ? null : movement.getAccount().getAccountId())
                .movementDate(movement.getMovementDate())
                .movementId(movement.getMovementId())
                .amount(movement.getAmount())
                .balanceAmount(movement.getBalanceAmount())
                .createAt(movement.getCreateAt())
                .updateAt(movement.getUpdateAt()).build();
    }
}
