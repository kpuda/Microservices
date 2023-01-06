package com.kp.user.service.tools;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper mapper;

    public User mapToUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }
}
