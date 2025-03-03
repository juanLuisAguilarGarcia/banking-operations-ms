package com.pichincha.infra.api.router.controller.dto.response.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDataDto;
import com.pichincha.infra.api.router.controller.dto.response.movement.MovementDto;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountStateDataDto {
    private Timestamp date;
    @JsonProperty("client_name")
    private String clientName;
    @JsonProperty("account_number")
    private Long accountNumber;
    @JsonProperty("account_type")
    private AccountTypeDto accountType;
    @JsonProperty("init_amount")
    private BigDecimal initAmount;
    @JsonProperty("is_active")
    private Boolean isActive;
    private List<MovementDataDto> movements;
    @JsonProperty("balance_amount")
    private BigDecimal balanceAmount;
}
