package com.pichincha.infra.api.router.controller.dto.response.movement;

import com.pichincha.infra.api.router.controller.dto.response.GenericResponseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementDto extends GenericResponseDTO {
    private MovementDataDto data;
}
