package com.dh.sp.graphql.dto;

public class TestAddressDto {

    public static AddressDto.AddressDtoBuilder getInstance(){
        return AddressDto.builder()
                .street("xalapa")
                .number(1);
    }
}
