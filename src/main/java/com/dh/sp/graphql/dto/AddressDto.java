package com.dh.sp.graphql.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String street;
    private Integer number;
}