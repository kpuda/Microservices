package com.kp.user.service.responses;

import com.kp.user.service.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseObject extends ResponseObject {

    private UserDto userDto;

    public UserResponseObject(int statusCode, String message, UserDto userDto) {
        super(statusCode, message);
        this.userDto = userDto;
    }
}
