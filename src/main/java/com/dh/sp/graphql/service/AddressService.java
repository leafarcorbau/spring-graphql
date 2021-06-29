package com.dh.sp.graphql.service;

import com.dh.sp.graphql.converter.AddressToAddressDto;
import com.dh.sp.graphql.converter.EmployeeToEmployeeDto;
import com.dh.sp.graphql.dto.AddressDto;
import com.dh.sp.graphql.dto.CreateAddressDto;
import com.dh.sp.graphql.dto.EmployeeDto;
import com.dh.sp.graphql.model.Address;
import com.dh.sp.graphql.model.Employee;
import com.dh.sp.graphql.repo.AddressRepository;
import com.dh.sp.graphql.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressToAddressDto addressToAddressDto;
    private final AddressRepository addressRepository;

    public AddressDto create(final CreateAddressDto createAddressDto){
        final Address address = Address.builder()
                .employeeId(createAddressDto.getEmployeeId())
                .number(createAddressDto.getHouseNumber())
                .street(createAddressDto.getStreetName())
                .build();
        return addressToAddressDto.convert(addressRepository.save(address));
    }

    public Optional<AddressDto> findByEmployeeId(final Integer employeeId){
        final Optional<Address> optionalAddress = addressRepository.findByEmployeeId(employeeId);
        return optionalAddress.isPresent()? Optional.of(addressToAddressDto.convert(optionalAddress.get())):
                Optional.empty();
    }

    public void deleteByEmployeeId(final Integer employeeId) {
        addressRepository.removeByEmployeeId(employeeId);
    }
}
