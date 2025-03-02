package com.pichincha.infra.api.router.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreateAccountDto {
    @JsonProperty("account_client_id")
    @NotNull
    private Long clientId;
    @JsonProperty("account_number")
    @NotNull
    private Long accountNumber;
    @JsonProperty("account_type_id")
    @NotNull
    private Long accountTypeId;
    @JsonProperty("init_amount")
    @NotNull
    private BigDecimal initAmount;
}
