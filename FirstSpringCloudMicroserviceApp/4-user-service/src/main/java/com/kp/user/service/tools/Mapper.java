package com.kp.user.service.tools;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper modelMapper;

    public User mapToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto maptoUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
