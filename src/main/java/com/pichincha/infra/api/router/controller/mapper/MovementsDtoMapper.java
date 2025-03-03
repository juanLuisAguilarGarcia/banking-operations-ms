package com.pichincha.infra.api.router.controller.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.domain.entities.AccountType;
import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.api.router.controller.dto.request.CreateMovementDto;
import com.pichincha.infra.api.router.controller.dto.request.UpdateMovementDto;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MovementsDtoMapper {

    static Movement toEntity(CreateMovementDto createMovementDto, Long movementId){
        if(Objects.isNull(createMovementDto)){
            return Movement.builder().build();
        }

        Movement movement = Movement.builder()
                .movementId(movementId)
                .accountId(createMovementDto.getAccountId())
                .movementDate(createMovementDto.getMovementDate())
                .amount(createMovementDto.getAmount()).build();

        return movement;
    }

    static Movement updateToEntity(UpdateMovementDto updateMovementDto, Long movementId){
        if(Objects.isNull(updateMovementDto)){
            return Movement.builder().build();
        }

        Movement movement = Movement.builder()
                .movementId(movementId)
                .movementDate(updateMovementDto.getMovementDate())
                .build();

        return movement;
    }
}
