package com.dh.sp.graphql.converter;

import com.dh.sp.graphql.dto.CreateEmployeeDto;
import com.dh.sp.graphql.dto.CreateEmployeeRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateEmployeeRequestDtoToCreateEmployeeDto implements Converter<CreateEmployeeRequestDto, CreateEmployeeDto> {
    @Override
    public CreateEmployeeDto convert(CreateEmployeeRequestDto source) {
        return CreateEmployeeDto.builder()
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .email(source.getEmail())
                .build();
    }
}
