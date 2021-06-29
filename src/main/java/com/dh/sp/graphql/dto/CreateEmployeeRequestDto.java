package com.dh.sp.graphql.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String streetName;
    private Integer houseNumber;
}