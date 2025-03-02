package com.pichincha.domain.port.db;

import com.pichincha.domain.entities.Account;
import com.pichincha.infra.adapter.db.entites.ClientsDto;

public interface ClientsPortRepository {
    Account save(ClientsDto client);
    Account getClientById(Long clientId);
    Account getClientByIdentificationTypeIdAndIdentificationNumber(Long typeId, Long number);
    void deleteClient(Long clientId);
    Account updateClient(ClientsDto client );
}
