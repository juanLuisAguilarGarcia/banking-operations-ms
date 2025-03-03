package com.pichincha.infra.adapter.http.feign;

import com.pichincha.infra.adapter.http.entities.client.ClientDto;
import com.pichincha.infra.adapter.http.exception.ClientFeingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientFeignFallback implements ClientFeign {
    @Override
    public ClientDto getClientById(Long clientId) throws ClientFeingException {
        log.info("run ClientFeignFallback");
        throw new ClientFeingException("500-1", "Service client not response or error response");
    }
}
