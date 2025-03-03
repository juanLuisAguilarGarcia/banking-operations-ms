package com.pichincha.infra.api.router.controller.dto.response.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountTypeDto {
    @JsonProperty("account_type_id")
    private Long accountTypeId;
    private String description;
}
