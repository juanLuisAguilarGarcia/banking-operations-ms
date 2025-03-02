package com.pichincha.infra.api.router.controller.mapper;

import com.pichincha.domain.entities.Client;
import com.pichincha.domain.entities.ContactInformation;
import com.pichincha.domain.entities.Identification;
import com.pichincha.domain.entities.Person;
import com.pichincha.infra.api.router.controller.dto.request.CreateClientDto;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ClientDtoMapper {

    static Client toEntity(CreateClientDto createClientDto){
        if(Objects.isNull(createClientDto)){
            return Client.builder().build();
        }

        Client client = Client.builder()
                .clientId(createClientDto.getClientId())
                .password(createClientDto.getPassword())
                .isActive(createClientDto.getIsActive())
                .createAt(createClientDto.getCreateAt())
                .updateAt(createClientDto.getUpdateAt()).build();

        if(!Objects.isNull(createClientDto.getPersonalInformation())){
            client.setPersonalInformation(Person.builder()
                    .gender(createClientDto.getPersonalInformation().getGender())
                    .lastName(createClientDto.getPersonalInformation().getLastName())
                    .firstName(createClientDto.getPersonalInformation().getFirstName())
                    .age(createClientDto.getPersonalInformation().getAge())
                    .address(createClientDto.getPersonalInformation().getAddress()).build());

            if(!Objects.isNull(createClientDto.getPersonalInformation().getIdentification())){
                client.getPersonalInformation().setIdentification(Identification.builder()
                        .typeId(createClientDto.getPersonalInformation().getIdentification().getTypeId())
                        .number(createClientDto.getPersonalInformation().getIdentification().getNumber()).build());
            }

            if(!Objects.isNull(createClientDto.getPersonalInformation().getContactInformation())){
                client.getPersonalInformation().setContactInformation(ContactInformation.builder()
                        .telephoneNumber(createClientDto.getPersonalInformation().getContactInformation().getTelephoneNumber()).build());
            }

        }

        return client;
    }
}
