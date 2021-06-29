package com.dh.sp.graphql.manager;

import com.dh.sp.graphql.config.NoGraphqlTestConfig;
import com.dh.sp.graphql.dto.*;
import com.dh.sp.graphql.exception.EmailAlreadyExist;
import com.dh.sp.graphql.model.Address;
import com.dh.sp.graphql.model.Employee;
import com.dh.sp.graphql.repo.AddressRepository;
import com.dh.sp.graphql.repo.EmployeeRepository;
import com.dh.sp.graphql.utils.TestDBUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@Import(value = {NoGraphqlTestConfig.class})
public class EmployeeManagerIntegrationTest {

    @Autowired
    private EmployeeManager employeeManager;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TestDBUtils testDBUtils;

    @BeforeEach
    public void init(){
        testDBUtils.cleanDB();
    }
    @AfterEach
    public void clean(){
        testDBUtils.cleanDB();
    }

    @Test
    public void shouldCreateEmployee(){
        //Given
        final CreateEmployeeRequestDto createEmployeeRequestDto = TestCreateEmployeeRequestDto.getInstance().build();

        //When
        final EmployeeDto result = employeeManager.createEmployee(createEmployeeRequestDto);

        //Then
        final EmployeeDto expected = TestEmployeeDto.getInstance(result.getId())
                .address(TestAddressDto.getInstance().build()).build();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldFailCreateEmployeeWithExistentEmail(){
        //Given
        employeeManager.createEmployee(TestCreateEmployeeRequestDto.getInstance().build());
        final CreateEmployeeRequestDto createEmployeeRequestDto = TestCreateEmployeeRequestDto.getInstance().build();

        //When/Then
        assertThatExceptionOfType(EmailAlreadyExist.class).isThrownBy(()-> employeeManager.createEmployee(createEmployeeRequestDto));
    }

    @Test
    public void shouldDeleteEmployee(){
        //Given
        final CreateEmployeeRequestDto createEmployeeRequestDto = TestCreateEmployeeRequestDto.getInstance().build();
        final EmployeeDto employeeDto = employeeManager.createEmployee(createEmployeeRequestDto);
        final Integer id = employeeDto.getId();
        //When

        final Integer result = employeeManager.deleteEmployee(id);

        //Then
        assertThat(result).isEqualTo(id);
        final Optional<Employee> employeeOptional = employeeRepository.findById(id);
        assertThat(employeeOptional.isPresent()).isFalse();
        final Optional<Address> addressOptional = addressRepository.findByEmployeeId(id);
        assertThat(addressOptional.isPresent()).isFalse();
    }

    @Test
    public void shouldFindEmployeeById(){
        //Given
        final CreateEmployeeRequestDto createEmployeeRequestDto = TestCreateEmployeeRequestDto.getInstance().build();
        final EmployeeDto employeeDto = employeeManager.createEmployee(createEmployeeRequestDto);
        final EmployeeDto expected = TestEmployeeDto.getInstance(employeeDto.getId())
                .address(TestAddressDto.getInstance().build()).build();
        //When
        final Optional<EmployeeDto> result = employeeManager.findEmployeeById(employeeDto.getId());

        //Then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(expected);
    }

    @Test
    public void shouldFindAllEmployees(){
        //Given
        final CreateEmployeeRequestDto createEmployeeRequestDto = TestCreateEmployeeRequestDto.getInstance().build();
        final EmployeeDto employeeDto = employeeManager.createEmployee(createEmployeeRequestDto);
        final List<EmployeeDto> expected = List.of(TestEmployeeDto.getInstance(employeeDto.getId())
                .address(TestAddressDto.getInstance().build()).build());
        //When
        final List<EmployeeDto> result = employeeManager.findAllEmployees();

        //Then
        assertThat(result.isEmpty()).isFalse();
        assertThat(result).isEqualTo(expected);
    }
}
