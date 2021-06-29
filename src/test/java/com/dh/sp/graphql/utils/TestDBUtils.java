package com.dh.sp.graphql.utils;

import com.dh.sp.graphql.repo.AddressRepository;
import com.dh.sp.graphql.repo.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.graphql.spring.boot.test.GraphQLResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestDBUtils {

    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void cleanDB(){
        addressRepository.deleteAll();
        employeeRepository.deleteAll();
    }
}
