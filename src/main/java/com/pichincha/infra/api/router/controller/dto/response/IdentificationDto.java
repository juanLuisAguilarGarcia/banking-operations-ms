package com.pichincha.infra.api.router.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdentificationDto {
    @NotBlank
    @JsonProperty("type_id")
    private Long typeId;
    @NotNull
    private Long number;
}
