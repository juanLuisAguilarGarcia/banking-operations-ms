package com.pichincha.infra.api.router.controller.dto.response.account;

import com.pichincha.infra.api.router.controller.dto.response.GenericResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountStateDto extends GenericResponseDTO {
    private List<AccountStateDataDto> data;
}
