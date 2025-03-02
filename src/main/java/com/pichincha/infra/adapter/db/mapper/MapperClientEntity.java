package com.pichincha.infra.adapter.db.mapper;

import com.pichincha.domain.entities.Client;
import com.pichincha.domain.entities.ContactInformation;
import com.pichincha.domain.entities.Identification;
import com.pichincha.domain.entities.Person;
import com.pichincha.infra.adapter.db.entites.ClientsDto;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MapperClientEntity {

    static Client toClient(ClientsDto client){
        if (Objects.isNull(client)) {
            return Client.builder().build();
        }

        Person person = Person.builder().build();

        if (!Objects.isNull(client.getPersons())){
            person = Person.builder()
                    .personId(client.getPersons().getPersonId())
                    .address(client.getPersons().getAddress())
                    .age(client.getPersons().getAge())
                    .firstName(client.getPersons().getFirstName())
                    .lastName(client.getPersons().getLastName())
                    .gender(client.getPersons().getGender())
                    .identification(Identification.builder()
                            .number(client.getPersons().getIdentificationNumber()).build())
                    .contactInformation(ContactInformation.builder()
                            .telephoneNumber(client.getPersons().getTelephonNumber()).build()).build();

            if (!Objects.isNull(client.getPersons().getIdentification())){
                person.getIdentification().setTypeId(client.getPersons().getIdentification().getIdentificationTypeId());
            }
        }

        return Client.builder()
                .isActive(client.getIsActive())
                .clientId(client.getClientId())
                .password(client.getPassword())
                .createAt(client.getCreateAt())
                .updateAt(client.getUpdateAt())
                .personalInformation(person).build();
    }
}
