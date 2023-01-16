package com.kp.users.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel extends ResponseModel{

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponseModel> albums;
}
