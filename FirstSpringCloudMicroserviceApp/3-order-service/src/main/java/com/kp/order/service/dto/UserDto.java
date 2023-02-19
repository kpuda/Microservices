package com.kp.order.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private List<Long> orderList= new ArrayList<>();

    public UserDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto(String firstName, String lastName, List<Long> orderList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderList = orderList;
    }
}
