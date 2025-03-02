package com.pichincha.infra.api.router.controller.dto.response.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pichincha.infra.api.router.controller.dto.request.CreateClientDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDataDto extends CreateClientDto {
    @JsonProperty("client_id")
    private Long clientId;
}
