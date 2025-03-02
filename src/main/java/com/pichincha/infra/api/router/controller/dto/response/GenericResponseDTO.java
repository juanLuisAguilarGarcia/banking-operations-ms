package com.pichincha.infra.api.router.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.pichincha.infra.api.router.controller.error.ErrorConsts.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDTO {
    @Schema(example = MOCK_CODE_200)
    private String code;
    @Schema(example = MOCK_MENSAJE_OK)
    private String message;
}
