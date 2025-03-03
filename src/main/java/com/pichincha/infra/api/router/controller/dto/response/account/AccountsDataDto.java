package com.pichincha.infra.api.router.controller.dto.response.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsDataDto {
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("account_client_id")
    private Long clientId;
    @JsonProperty("account_number")
    private Long accountNumber;
    @JsonProperty("account_type")
    private AccountTypeDto accountType;
    @JsonProperty("init_amount")
    private BigDecimal initAmount;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("create_at")
    private Timestamp createAt;
    @JsonProperty("update_at")
    private Timestamp updateAt;
}
