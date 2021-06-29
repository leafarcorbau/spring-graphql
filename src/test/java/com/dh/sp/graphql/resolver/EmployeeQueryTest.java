package com.dh.sp.graphql.resolver;

import com.dh.sp.graphql.config.TestConfig;
import com.dh.sp.graphql.dto.AddressDto;
import com.dh.sp.graphql.dto.EmployeeDto;
import com.dh.sp.graphql.dto.TestAddressDto;
import com.dh.sp.graphql.dto.TestEmployeeDto;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@Import(value = TestConfig.class)
@GraphQLTest
public class EmployeeQueryTest {

	@Autowired
	protected TestUtils testUtils;
	@Autowired
	protected GraphQLTestTemplate graphQLTestTemplate;
	@Autowired
	private EmployeeManager employeeManager;

	@Test
	public void shouldFindEmployeeById() throws IOException{
		//Given
		final Integer employeeId = 1;
		final AddressDto addressDto = TestAddressDto.getInstance().build();
		final EmployeeDto employeeDto = TestEmployeeDto.getInstance(employeeId)
				.address(addressDto)
				.build();

		doReturn(Optional.of(employeeDto)).when(employeeManager).findEmployeeById(1);
		//When
		GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("graphql/employee_find_by_id_request.graphqls");

		//Then
		assertThat(graphQLResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		Optional<EmployeeDto> result = testUtils.graphQLResponseToObject(graphQLResponse, "employee", EmployeeDto.class);
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get()).isEqualTo(employeeDto);
	}

	@Test
	public void shouldFindAllEmployees() throws IOException{
		//Given
		final Integer employeeId = 1;
		final AddressDto addressDto = TestAddressDto.getInstance().build();
		final EmployeeDto employeeDto = TestEmployeeDto.getInstance(employeeId)
				.address(addressDto)
				.build();

		List<EmployeeDto> expected = List.of(employeeDto);
		doReturn(expected).when(employeeManager).findAllEmployees();
		//When
		GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("graphql/employee_find_all_request.graphqls");

		//Then
		assertThat(graphQLResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<EmployeeDto> result = testUtils.graphQLResponseToObjectList(graphQLResponse, "employees", EmployeeDto.class);
		assertThat(result.isEmpty()).isFalse();
		assertThat(result).isEqualTo(expected);
	}

}
