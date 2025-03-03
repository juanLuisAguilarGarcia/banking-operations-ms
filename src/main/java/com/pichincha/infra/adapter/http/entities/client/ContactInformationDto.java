package com.pichincha.infra.adapter.http.entities.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInformationDto {
    @JsonProperty("telephone_number")
    private Long telephoneNumber;
}
