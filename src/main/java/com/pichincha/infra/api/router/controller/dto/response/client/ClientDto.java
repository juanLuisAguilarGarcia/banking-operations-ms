package com.pichincha.infra.api.router.controller.dto.response.client;

import com.pichincha.infra.api.router.controller.dto.response.GenericResponseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto extends GenericResponseDTO {
    private ClientDataDto data;
}
