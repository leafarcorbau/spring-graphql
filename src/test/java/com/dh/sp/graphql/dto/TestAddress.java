package com.dh.sp.graphql.dto;

import com.dh.sp.graphql.model.Address;

public class TestAddress {

    public static Address.AddressBuilder getInstance(){
        return Address.builder()
                .street("xalapa")
                .number(1);
    }
}
