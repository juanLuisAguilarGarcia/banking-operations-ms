package com.pichincha.infra.adapter.db.entites;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movements")
public class MovementsDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "movement_id")
    private Long movementId;

    private BigDecimal amount;

    @Column(name = "balance_amount")
    private BigDecimal balanceAmount;

    @Column(name = "movement_date" )
    private Timestamp movementDate;

    @Column(name = "create_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Timestamp createAt;

    @Column(name = "update_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updateAt;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "movement_account_id", referencedColumnName = "account_id")
    private AccountsDto account;


}
