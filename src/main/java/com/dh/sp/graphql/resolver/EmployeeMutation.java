package com.dh.sp.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.dh.sp.graphql.dto.CreateEmployeeRequestDto;
import com.dh.sp.graphql.dto.EmployeeDto;
import com.dh.sp.graphql.manager.EmployeeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMutation implements GraphQLMutationResolver {

    private final EmployeeManager employeeManager;

    public EmployeeDto createEmployee(final CreateEmployeeRequestDto createEmployeeRequestDto){
        return employeeManager.createEmployee(createEmployeeRequestDto);
    }

    public Integer deleteEmployee(final Integer employeeId){
        return employeeManager.deleteEmployee(employeeId);
    }
}
