package com.dh.sp.graphql.repository;

import com.dh.sp.graphql.config.NoGraphqlTestConfig;
import com.dh.sp.graphql.dto.TestEmployee;
import com.dh.sp.graphql.model.Employee;
import com.dh.sp.graphql.repo.EmployeeRepository;
import com.dh.sp.graphql.utils.TestDBUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(value = {NoGraphqlTestConfig.class})
public class EmployeeRepositoryIntegrationTest {

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
    public void saveEmployee(){
        //Given;
        final Employee employee = TestEmployee.getInstance().build();

        //When
        final Employee result = employeeRepository.save(employee);

        //Then
        final Optional<Employee> expected = employeeRepository.findById(result.getId());
        assertThat(expected.isEmpty()).isFalse();
        assertThat(result).isEqualTo(expected.get());
    }

    @Test
    public void shouldFindByEmail(){
        //Given
        final Employee employee = TestEmployee.getInstance().build();
        final Employee expected =employeeRepository.save(employee);

        //When
        final Optional<Employee> result = employeeRepository.findByEmail(employee.getEmail());

        //Then
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get()).isEqualTo(expected);
    }

}
