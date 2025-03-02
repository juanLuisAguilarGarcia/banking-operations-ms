package com.pichincha.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private Long personId;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private Identification identification;
    private String address;
    private ContactInformation contactInformation;
}
