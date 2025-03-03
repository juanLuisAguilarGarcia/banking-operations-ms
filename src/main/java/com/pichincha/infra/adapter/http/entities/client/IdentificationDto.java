package com.pichincha.infra.adapter.http.entities.client;

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
    @JsonProperty("type_id")
    private Long typeId;
    @NotNull
    private Long number;
}
