package com.dh.sp.graphql.repo;

import com.dh.sp.graphql.model.Address;
import com.dh.sp.graphql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByEmployeeId(Integer employeeId);
    Integer removeByEmployeeId(Integer employeeId);
}
