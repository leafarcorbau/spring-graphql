package com.dh.sp.graphql.converter;

import com.dh.sp.graphql.dto.AddressDto;
import com.dh.sp.graphql.dto.EmployeeDto;
import com.dh.sp.graphql.model.Address;
import com.dh.sp.graphql.model.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressDto implements Converter<Address, AddressDto> {

    @Override
    public AddressDto convert(Address source) {
        return AddressDto.builder()
                .number(source.getNumber())
                .street(source.getStreet())
                .build();
    }
}
