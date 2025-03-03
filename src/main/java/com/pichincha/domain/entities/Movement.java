package com.pichincha.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
public class Movement {
    private Long movementId;
    private Long accountId;
    private BigDecimal amount;
    private BigDecimal balanceAmount;
    private Timestamp movementDate;
    private Timestamp createAt;
    private Timestamp updateAt;
}
