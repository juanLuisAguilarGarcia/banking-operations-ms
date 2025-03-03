package com.pichincha.infra.adapter.http.entities.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDataDto {
    @JsonProperty("client_id")
    private Long clientId;
    @JsonProperty("is_active")
    private Boolean isActive;
    private String password;
    @JsonProperty("personal_information")
    private PersonDto personalInformation;
    @JsonProperty("create_at")
    private Timestamp createAt;
    @JsonProperty("update_at")
    private Timestamp updateAt;
}
