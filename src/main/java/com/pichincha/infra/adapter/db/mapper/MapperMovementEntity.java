package com.pichincha.infra.adapter.db.mapper;

import com.pichincha.domain.entities.Client;
import com.pichincha.domain.entities.Movement;
import com.pichincha.infra.adapter.db.entites.MovementsDto;
import com.pichincha.infra.adapter.http.entities.client.ClientDto;
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

    static Client toClient(ClientDto client){
        if (Objects.isNull(client)) {
            return Client.builder().build();
        }

        String nombre = "";

        if(!Objects.isNull(client.getData()) && !Objects.isNull(client.getData().getPersonalInformation())) {
            nombre = client.getData().getPersonalInformation().getFirstName()
                    .concat(" ").concat(client.getData().getPersonalInformation().getLastName());
        }
        return Client.builder()
                .clientName(nombre).build();
    }
}
