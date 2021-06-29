package com.dh.sp.graphql.service;

import com.dh.sp.graphql.converter.EmployeeToEmployeeDto;
import com.dh.sp.graphql.dto.CreateEmployeeDto;
import com.dh.sp.graphql.dto.EmployeeDto;
import com.dh.sp.graphql.exception.EmailAlreadyExist;
import com.dh.sp.graphql.model.Employee;
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
public class EmployeeService {

    private final EmployeeToEmployeeDto employeeToEmployeeDto;
    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll(){
        List<Employee> employees = employeeRepository.findAll();
        return CollectionUtils.isNotEmpty(employees)?
                employees.stream()
                        .map(employee -> employeeToEmployeeDto.convert(employee))
                        .collect(Collectors.toList()):
                Collections.EMPTY_LIST;
    }

    public Optional<EmployeeDto> findById(final int id){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.isPresent()?
                Optional.of(employeeToEmployeeDto.convert(optionalEmployee.get())):
                Optional.empty();
    }

    public EmployeeDto create(final CreateEmployeeDto createEmployeeDto) throws EmailAlreadyExist {

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(createEmployeeDto.getEmail());

        if(optionalEmployee.isPresent()){
            throw new EmailAlreadyExist(createEmployeeDto.getEmail());
        }

        final Employee emp = Employee.builder()
                .email(createEmployeeDto.getEmail())
                .firstName(createEmployeeDto.getFirstName())
                .lastName(createEmployeeDto.getLastName())
                .build();
        return employeeToEmployeeDto.convert(employeeRepository.save(emp));
    }

    public void deleteId(final Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
