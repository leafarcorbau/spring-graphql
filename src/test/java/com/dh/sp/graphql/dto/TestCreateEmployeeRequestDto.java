package com.dh.sp.graphql.dto;

public class TestCreateEmployeeRequestDto {

    public static CreateEmployeeRequestDto.CreateEmployeeRequestDtoBuilder getInstance(){
        return CreateEmployeeRequestDto.builder()
                .email("r@gmail.com")
                .firstName("rafa")
                .lastName("cornelio")
                .houseNumber(1)
                .streetName("xalapa");

    }
}
