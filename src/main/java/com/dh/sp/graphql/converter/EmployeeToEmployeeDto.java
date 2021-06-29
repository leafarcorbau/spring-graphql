package com.dh.sp.graphql.converter;

import com.dh.sp.graphql.dto.AddressDto;
import com.dh.sp.graphql.dto.EmployeeDto;
import com.dh.sp.graphql.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToEmployeeDto implements Converter<Employee, EmployeeDto> {

    @Override
    public EmployeeDto convert(Employee source) {

        return EmployeeDto.builder()
                .id(source.getId())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName()).build();
    }
}
