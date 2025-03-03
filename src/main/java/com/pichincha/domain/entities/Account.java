package com.pichincha.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
public class Account {
    private Long accountId;
    private Long clientId;
    private Long accountNumber;
    private List<Movement> movements;
    private AccountType accountType;
    private BigDecimal initAmount;
    private Boolean isActive;
    private Timestamp createAt;
    private Timestamp updateAt;
}
