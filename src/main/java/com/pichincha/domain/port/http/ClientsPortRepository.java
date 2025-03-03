package com.pichincha.domain.port.http;

import com.pichincha.domain.entities.Client;
import com.pichincha.infra.adapter.http.exception.ClientFeingException;

public interface ClientsPortRepository {
    Client getClientById(Long clientId) throws ClientFeingException;
}
