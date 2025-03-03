package com.pichincha.infra.api.router.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreateMovementDto {
    @JsonProperty("movement_account_id")
    @NotNull
    private Long accountId;
    @NotNull
    private BigDecimal amount;
    @JsonProperty("movement_date")
    @NotNull
    private Timestamp movementDate;
}
