package com.dh.sp.graphql.manager;

import com.dh.sp.graphql.converter.CreateEmployeeRequestDtoToCreateAddressDto;
import com.dh.sp.graphql.converter.CreateEmployeeRequestDtoToCreateEmployeeDto;
import com.dh.sp.graphql.dto.*;
import com.dh.sp.graphql.service.AddressService;
import com.dh.sp.graphql.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeManager {

    private final EmployeeService employeeService;
    private final AddressService addressService;
    private final CreateEmployeeRequestDtoToCreateAddressDto createEmployeeRequestDtoToCreateAddressDto;
    private final CreateEmployeeRequestDtoToCreateEmployeeDto createEmployeeRequestDtoToCreateEmployeeDto;

    @Transactional
    public EmployeeDto createEmployee(final CreateEmployeeRequestDto createEmployeeRequestDto){

        final CreateEmployeeDto createEmployeeDto = createEmployeeRequestDtoToCreateEmployeeDto.convert(createEmployeeRequestDto);
        EmployeeDto employeeDto = employeeService.create(createEmployeeDto);
        final CreateAddressDto createAddressDto = createEmployeeRequestDtoToCreateAddressDto.convert(createEmployeeRequestDto, employeeDto.getId());
        final AddressDto addressDto = addressService.create(createAddressDto);
        employeeDto.setAddress(addressDto);
        return employeeDto;
    }

    @Transactional
    public Integer deleteEmployee(final Integer employeeId){
        addressService.deleteByEmployeeId(employeeId);
        employeeService.deleteId(employeeId);
        return employeeId;
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAllEmployees(){
        List<EmployeeDto> employeeDtos = employeeService.findAll();
        employeeDtos.forEach(employeeDto ->
                addressService.findByEmployeeId(employeeDto.getId())
                        .ifPresent(address -> employeeDto.setAddress(address))
        );

        return employeeDtos;
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeDto> findEmployeeById(final Integer id){
        Optional<EmployeeDto> optionalEmployeeDto = employeeService.findById(id);

        optionalEmployeeDto.ifPresent(employeeDto ->
                addressService.findByEmployeeId(employeeDto.getId())
                        .ifPresent(address -> employeeDto.setAddress(address))
        );

        return optionalEmployeeDto;
    }
}
