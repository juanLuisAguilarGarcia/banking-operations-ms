package com.pichincha.app.mapper;

import com.pichincha.domain.entities.Account;
import com.pichincha.infra.adapter.db.entites.ClientsDto;
import com.pichincha.infra.adapter.db.entites.IdentificationTypeDto;
import com.pichincha.infra.adapter.db.entites.PersonsDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountsDataDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import org.mapstruct.Mapper;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    static AccountsDataDto toDto(Account account){
        AccountsDataDto accountsDataDto = AccountsDataDto.builder().clientId(account.getClientId()).build();

        accountsDataDto.setPassword(account.getPassword());
        accountsDataDto.setIsActive(account.getIsActive());
        accountsDataDto.setPassword(account.getPassword());
        accountsDataDto.setCreateAt(account.getCreateAt());
        accountsDataDto.setUpdateAt(account.getUpdateAt());

        if(!Objects.isNull(account.getPersonalInformation())){
            accountsDataDto.setPersonalInformation(PersonDto.builder()
                    .address(account.getPersonalInformation().getAddress())
                    .age(account.getPersonalInformation().getAge())
                    .firstName(account.getPersonalInformation().getFirstName())
                    .lastName(account.getPersonalInformation().getLastName())
                    .gender(account.getPersonalInformation().getGender()).build());

            if(!Objects.isNull(account.getPersonalInformation().getContactInformation())){
                accountsDataDto.getPersonalInformation().setContactInformation(ContactInformationDto.builder()
                        .telephoneNumber(account.getPersonalInformation().getContactInformation().getTelephoneNumber()).build());
            }

            if(!Objects.isNull(account.getPersonalInformation().getIdentification())){
                accountsDataDto.getPersonalInformation().setIdentification(IdentificationDto.builder()
                        .number(account.getPersonalInformation().getIdentification().getNumber())
                        .typeId(account.getPersonalInformation().getIdentification().getTypeId()).build());
            }
        }

        return accountsDataDto;
    }

    static AccountDto toClientDto(AccountsDataDto client, String msg){
        AccountDto clientDto =  AccountDto.builder().data(client).build();
        clientDto.setCode(String.valueOf(HttpStatus.OK.value()));
        clientDto.setMessage(msg);

        return clientDto;
    }

    static ClientsDto toClientEntityDto(Account account) {
        PersonsDto personsDto = new PersonsDto();

        if (Objects.isNull(account) ){
            return ClientsDto.builder().build();
        }

        if(!Objects.isNull(account.getPersonalInformation())) {
            personsDto = PersonsDto.builder()
                    .personId(account.getPersonalInformation().getPersonId())
                    .address(account.getPersonalInformation().getAddress())
                    .age(account.getPersonalInformation().getAge())
                    .firstName(account.getPersonalInformation().getFirstName())
                    .lastName(account.getPersonalInformation().getLastName())
                    .gender(account.getPersonalInformation().getGender())
                    .telephonNumber(account.getPersonalInformation().getContactInformation().getTelephoneNumber())
                    .updateAt(Timestamp.valueOf(LocalDateTime.now()))
                    .identification(IdentificationTypeDto.builder().build()).build();

            if(!Objects.isNull(account.getPersonalInformation().getContactInformation())) {
                personsDto.setIdentificationNumber(account.getPersonalInformation().getIdentification().getNumber());
            }

            if(!Objects.isNull(account.getPersonalInformation().getIdentification())) {
                personsDto.setIdentification(IdentificationTypeDto.builder().identificationTypeId(
                        account.getPersonalInformation().getIdentification().getTypeId()
                ).build());
            }
        }

        return  ClientsDto.builder()
                .clientId(account.getClientId())
                .isActive(true)
                .createAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .updateAt(java.sql.Timestamp.valueOf(LocalDateTime.now()))
                .persons(personsDto)
                .password(account.getPassword()).build();
    }
}
