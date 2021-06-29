package com.dh.sp.graphql.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDto {
    private Integer employeeId;
    private String streetName;
    private Integer houseNumber;
}