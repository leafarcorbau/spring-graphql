package com.dh.sp.graphql.dto;

import com.dh.sp.graphql.model.Employee;

public class TestEmployee {

    public static Employee.EmployeeBuilder getInstance(){
        return Employee.builder()
                .email("r@gmail.com")
                .firstName("rafa")
                .lastName("cornelio");
    }
}
