package com.pichincha.domain.port.db;

import com.pichincha.domain.entities.Client;
import com.pichincha.infra.adapter.db.entites.ClientsDto;

public interface ClientsPortRepository {
    Client save(ClientsDto client);
    Client getClientById(Long clientId);
    Client getClientByIdentificationTypeIdAndIdentificationNumber(Long typeId, Long number);
    void deleteClient(Long clientId);
    Client updateClient(ClientsDto client );
}
