package com.dh.sp.graphql.converter;

import com.dh.sp.graphql.dto.CreateAddressDto;
import com.dh.sp.graphql.dto.CreateEmployeeDto;
import com.dh.sp.graphql.dto.CreateEmployeeRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateEmployeeRequestDtoToCreateAddressDto {
    public CreateAddressDto convert(CreateEmployeeRequestDto source, final Integer employeeId) {
        return CreateAddressDto.builder()
                .employeeId(employeeId)
                .houseNumber(source.getHouseNumber())
                .streetName(source.getStreetName())
                .build();
    }
}
