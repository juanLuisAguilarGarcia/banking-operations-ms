package com.pichincha.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Identification {
    private Long typeId;
    private Long number;
}
