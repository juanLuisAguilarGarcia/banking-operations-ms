package com.pichincha.app;

import com.pichincha.app.mapper.ClientMapper;
import com.pichincha.domain.entities.Account;
import com.pichincha.domain.port.db.ClientsPortRepository;
import com.pichincha.infra.adapter.db.entites.ClientsDto;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountDto;
import com.pichincha.infra.api.router.controller.error.exception.AccountException;
import com.pichincha.infra.api.router.facade.AccountsFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.pichincha.app.ServiceConsts.*;

@Slf4j
@Service
public class ClientsService implements AccountsFacade {

    @Autowired
    private ClientsPortRepository clientsPortRepository;

    public AccountDto createClient(Account accountToSave) throws AccountException {
        try{
            Account account = clientsPortRepository.save(
                    ClientMapper.toClientEntityDto(
                            validClientBeforeCreate(accountToSave)));

            account.setCreateAt(account.getUpdateAt());
            log.info(String.format(MSG_PROCESS_SERVICE, "created", "identification_number: ", accountToSave.getPersonalInformation().getIdentification().getNumber()));
            return ClientMapper.toClientDto(ClientMapper.toDto(account), MSG_CLIENT_CREATED);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "created",  "identification_number: ", accountToSave.getPersonalInformation().getIdentification().getNumber(),
                    ex.getMessage()));
            throw new AccountException("422-1", ex.getMessage());
        }
    }

    public AccountDto getClientById(Long clientId) throws AccountException {
        try{
            Account account = existsClient(clientId);
            log.info(String.format(MSG_PROCESS_SERVICE, "getById", "clientId: ", clientId));
            return ClientMapper.toClientDto(ClientMapper.toDto(account), MSG_CLIENT_GET);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "getById",  "clientId: ", clientId,
                    ex.getMessage()));
            throw new AccountException("422-2", ex.getMessage());
        }

    }

    public void deleteClient(Long clientId) throws AccountException {
        try{
            Account account = existsClient(clientId);

            ClientsDto clientEntity = ClientMapper.toClientEntityDto(account);
            clientEntity.setIsActive(false);

            clientsPortRepository.save(clientEntity);
            log.info(String.format(MSG_PROCESS_SERVICE, "delete", "clientId: ", clientId));
            return;
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "delete",  "clientId: ", clientId,
                    ex.getMessage()));
            throw new AccountException("422-3", ex.getMessage());
        }
    }

    public AccountDto updateClient(Account accountToUpdate) throws AccountException {
        try{
            Account accountExists = existsClient(accountToUpdate.getClientId());

            accountToUpdate.getPersonalInformation().setPersonId(accountExists.getPersonalInformation().getPersonId());
            Account account = clientsPortRepository.updateClient(ClientMapper.toClientEntityDto(accountToUpdate));
            log.info(String.format(MSG_PROCESS_SERVICE, "update", "clientId: ", accountToUpdate.getClientId()));
            return ClientMapper.toClientDto(ClientMapper.toDto(account), MSG_CLIENT_GET);
        } catch(DataAccessException ex){
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "update",  "clientId: ", accountToUpdate.getClientId(),
                    ex.getMessage()));
            throw new AccountException("422-4", ex.getMessage());
        }
    }

    private Account existsClient(Long clientId) throws AccountException {
        Account account = clientsPortRepository.getClientById(clientId);

        if(Objects.isNull(account.getClientId())  || account.getClientId() < 1L) {
            log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "clientId: ", clientId,
                    "client not found"));
            throw new AccountException("422-5", "client not found");
        }

        return account;
    }

    private Account validClientBeforeCreate(Account accountToCreate) throws AccountException {
        Account account = clientsPortRepository.getClientByIdentificationTypeIdAndIdentificationNumber(
                accountToCreate.getPersonalInformation().getIdentification().getTypeId(), accountToCreate.getPersonalInformation().getIdentification().getNumber());

        if(!Objects.isNull(account.getClientId()) && account.getClientId() >= 1L ) {
            if(account.getIsActive()) {
                log.error(String.format(MSG_ERROR_PROCESS_SERVICE, "exists",  "identification_number: ", accountToCreate.getPersonalInformation().getIdentification().getNumber(),
                        "Client exists in system."));
                throw new AccountException("422-5", "Client exists in system.");
            } else {
                log.info(String.format(MSG_PROCESS_SERVICE, "exists so the information will be updated and actived", "clientId: ", account.getClientId()));
                accountToCreate.setClientId(account.getClientId());
                accountToCreate.getPersonalInformation().setPersonId(account.getPersonalInformation().getPersonId());
                accountToCreate.setIsActive(true);
                return accountToCreate;
            }
        }else {
            return accountToCreate;
        }

    }
}
