package com.pichincha.infra.adapter.db.entites;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "account_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTypeDto {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @Column(length = 100)
    private String description;

    @Column(name = "create_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable = false)
    private Timestamp createAt;

    @Column(name = "update_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updateAt;

    @OneToMany(mappedBy= "accountType")
    private List<AccountsDto> accounts;
}
