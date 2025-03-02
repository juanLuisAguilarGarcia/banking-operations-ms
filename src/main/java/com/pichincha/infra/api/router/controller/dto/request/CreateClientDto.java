package com.pichincha.infra.api.router.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pichincha.infra.api.router.controller.dto.response.PersonDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDto  {
    @JsonIgnore
    private Long clientId;
    @JsonProperty("is_active")
    private Boolean isActive;
    @NotBlank(message = "password is requiered")
    private String password;
    @JsonProperty("personal_information")
    @Valid
    private PersonDto personalInformation;
    @JsonProperty("create_at")
    private Timestamp createAt;
    @JsonProperty("update_at")
    private Timestamp updateAt;
}
