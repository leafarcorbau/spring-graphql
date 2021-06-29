package com.dh.sp.graphql.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
@Entity
@Table(name="address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="employee_id")
    private Integer employeeId;
    
    @Column(name="street")
    private String street;
    
    @Column(name="house_number")
    private Integer number;
}