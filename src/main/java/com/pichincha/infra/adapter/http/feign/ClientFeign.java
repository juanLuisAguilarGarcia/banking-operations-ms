package com.pichincha.infra.adapter.http.feign;

import com.pichincha.infra.adapter.http.entities.client.ClientDto;
import com.pichincha.infra.adapter.http.exception.ClientFeingException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clients", url = "${spring.cloud.openfeign.client.config.clients.url}",  fallback = ClientFeignFallback.class)
public interface ClientFeign {
    @GetMapping(value = "/{client_id}", produces = "application/json")
    ClientDto getClientById(@PathVariable("client_id") Long clientId) throws ClientFeingException;
}
