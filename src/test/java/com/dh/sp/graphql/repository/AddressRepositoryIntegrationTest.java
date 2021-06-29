package com.dh.sp.graphql.repository;

import com.dh.sp.graphql.config.NoGraphqlTestConfig;
import com.dh.sp.graphql.config.TestConfig;
import com.dh.sp.graphql.dto.TestAddress;
import com.dh.sp.graphql.dto.TestEmployee;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(value = {NoGraphqlTestConfig.class})
public class AddressRepositoryIntegrationTest {

    @Autowired
    private AddressRepository addressRepository;
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
    public void saveAddress(){
        //Given
        final Address address = TestAddress.getInstance().build();

        //When
        final Address result = addressRepository.save(address);

        //Then
        final Optional<Address> expected = addressRepository.findById(result.getId());
        assertThat(expected.isEmpty()).isFalse();
        assertThat(result).isEqualTo(expected.get());
    }

    @Test
    public void shouldFindByEmployeeId(){
        //Given
        final Integer addressId = 1;
        final Address address = TestAddress.getInstance().id(addressId).build();
        final Address expected = addressRepository.save(address);

        //When
        final Optional<Address> result = addressRepository.findByEmployeeId(address.getEmployeeId());

        //Then
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get()).isEqualTo(expected);
    }

    @Test
    @Transactional
    public void shouldRemoveByEmployeeId(){
        //Given
        final Integer addressId = 1;
        final Address address = TestAddress.getInstance().id(addressId).build();
        addressRepository.save(address);

        //When
        addressRepository.removeByEmployeeId(address.getEmployeeId());

        //Then
        final Optional<Address> result = addressRepository.findByEmployeeId(address.getEmployeeId());
        assertThat(result.isEmpty()).isTrue();
    }

}
