package com.dh.sp.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dh.sp.graphql.dto.EmployeeDto;
import com.dh.sp.graphql.manager.EmployeeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeQuery implements GraphQLQueryResolver {

    private final EmployeeManager employeeManager;

    public List<EmployeeDto> getEmployees(){
        return employeeManager.findAllEmployees();
    }
    public Optional<EmployeeDto> getEmployee(final Integer id){
        return employeeManager.findEmployeeById(id);
    }

}
