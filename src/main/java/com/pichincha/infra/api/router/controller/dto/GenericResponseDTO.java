package com.pichincha.infra.api.router.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static com.pichincha.infra.api.router.controller.error.ErrorConsts.MOCK_CODE;
import static com.pichincha.infra.api.router.controller.error.ErrorConsts.MOCK_MENSAJE_ERROR;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponseDTO {
    @Schema(example = MOCK_CODE)
    private String code;
    @Schema(example = MOCK_MENSAJE_ERROR)
    private String message;
}
