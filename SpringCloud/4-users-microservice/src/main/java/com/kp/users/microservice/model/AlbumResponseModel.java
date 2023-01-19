package com.kp.users.microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumResponseModel extends ResponseModel {

    private String albumId;
    private String userId;
    private String name;
    private String description;
}
