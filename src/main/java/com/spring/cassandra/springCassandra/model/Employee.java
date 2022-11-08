package com.spring.cassandra.springCassandra.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(value = "employee")
public class Employee {

    @PrimaryKey
    private int id;

    private String name;
    private String email;
    private String password;
    private String state;


    @Override
    public String toString() {
        return "EmployeeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
