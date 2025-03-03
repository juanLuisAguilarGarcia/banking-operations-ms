package com.pichincha.infra.api.router.controller.dto.response.account;

import com.pichincha.infra.api.router.controller.dto.response.GenericResponseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto extends GenericResponseDTO {
    private AccountsDataDto data;
}
