package com.dh.sp.graphql.dto;

public class TestEmployeeDto {

    public static EmployeeDto.EmployeeDtoBuilder getInstance(final int employeeId){
        return EmployeeDto.builder()
                .email("r@gmail.com")
                .firstName("rafa")
                .lastName("cornelio")
                .id(employeeId);
    }
}
