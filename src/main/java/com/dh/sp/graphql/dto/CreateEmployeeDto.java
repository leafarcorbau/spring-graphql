package com.dh.sp.graphql.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
}