package com.dh.sp.graphql.config;

import com.dh.sp.graphql.manager.EmployeeManager;
import com.dh.sp.graphql.repo.AddressRepository;
import com.dh.sp.graphql.repo.EmployeeRepository;
import com.dh.sp.graphql.service.AddressService;
import com.dh.sp.graphql.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan("com.dh.sp.graphql.utils")
public class TestConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    @Bean
    public EmployeeManager employeeManager(){
        return Mockito.mock(EmployeeManager.class);
    }
    @Bean
    public EmployeeService employeeService(){
        return Mockito.mock(EmployeeService.class);
    }
    @Bean
    public AddressService addressService(){
        return Mockito.mock(AddressService.class);
    }
    @Bean
    public EmployeeRepository employeeRepository(){
        return Mockito.mock(EmployeeRepository.class);
    }
    @Bean
    public AddressRepository addressRepository(){
        return Mockito.mock(AddressRepository.class);
    }
}
