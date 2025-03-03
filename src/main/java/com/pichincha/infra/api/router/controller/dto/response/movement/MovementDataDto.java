package com.pichincha.infra.api.router.controller.dto.response.movement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pichincha.infra.api.router.controller.dto.response.account.AccountTypeDto;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementDataDto {
    @JsonProperty("movement_id")
    private Long movementId;
    @JsonProperty("movement_account_id")
    private Long accountId;
    private BigDecimal amount;
    @JsonProperty("balance_amount")
    private BigDecimal balanceAmount;
    @JsonProperty("movement_date")
    private Timestamp movementDate;
    @JsonProperty("create_at")
    private Timestamp createAt;
    @JsonProperty("update_at")
    private Timestamp updateAt;
}
