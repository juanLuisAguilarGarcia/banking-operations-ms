package com.pichincha.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountType {
    private Long accountTypeId;
    private String description;
}
