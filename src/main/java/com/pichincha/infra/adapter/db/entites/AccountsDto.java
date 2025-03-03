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
@Table(name = "accounts")
public class AccountsDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_client_id")
    private Long clientId;

    @Column(name = "account_number")
    private Long accountNumber;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    private AccountTypeDto accountType;

    @Column(name = "init_amount")
    private BigDecimal initAmount;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "create_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Timestamp createAt;

    @Column(name = "update_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updateAt;
}
