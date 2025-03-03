package com.pichincha.infra.adapter.http.entities.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String gender;
    private Integer age;
    private IdentificationDto identification;
    private String address;
    @JsonProperty("contact_information")
    private ContactInformationDto contactInformation;
}
