package com.dh.sp.graphql.resolver;

import com.dh.sp.graphql.config.TestConfig;
import com.dh.sp.graphql.dto.*;
import com.dh.sp.graphql.manager.EmployeeManager;
import com.dh.sp.graphql.utils.TestUtils;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@Import(value = TestConfig.class)
@GraphQLTest
public class EmployeeMutationTest {

	@Autowired
	private TestUtils testUtils;
	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;
	@Autowired
	private EmployeeManager employeeManager;

	@Test
	public void shouldCreateEmployee() throws IOException{
		//Given
		final Integer employeeId = 1;
		final AddressDto addressDto = TestAddressDto.getInstance().build();
		final EmployeeDto employeeDto = TestEmployeeDto.getInstance(employeeId)
				.address(addressDto)
				.build();
		final CreateEmployeeRequestDto createEmployeeRequestDto = TestCreateEmployeeRequestDto.getInstance()
				.build();
		doReturn(employeeDto).when(employeeManager).createEmployee(createEmployeeRequestDto);
		//When

		GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("graphql/employee_create_request.graphqls");

		//Then
		assertThat(graphQLResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Optional<EmployeeDto> result = testUtils.graphQLResponseToObject(graphQLResponse, "createEmployee", EmployeeDto.class);
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get()).isEqualTo(employeeDto);
	}

	@Test
	public void shouldDeleteEmployee() throws IOException{
		//Given
		final Integer employeeId = 1;
		doReturn(employeeId).when(employeeManager).deleteEmployee(employeeId);
		//When

		GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("graphql/employee_delete_request.graphqls");

		//Then
		assertThat(graphQLResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Optional<Integer> result = testUtils.graphQLResponseToObject(graphQLResponse, "deleteEmployee", Integer.class);
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get()).isEqualTo(employeeId);
	}
}
