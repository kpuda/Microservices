package com.kp.order.service.responses;

import com.kp.order.service.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseObject extends ResponseObject {

    private UserDto userDto;

    public UserResponseObject(HttpStatus statusCode, String message, UserDto userDto) {
        super(statusCode, message);
        this.userDto = userDto;
    }
}
