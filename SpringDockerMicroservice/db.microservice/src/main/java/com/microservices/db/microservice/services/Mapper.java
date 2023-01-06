package com.microservices.db.microservice.services;

import com.microservices.db.microservice.models.User;
import com.microservices.db.microservice.models.UserDto;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static ModelMapper modelMapper = new ModelMapper();

    private Mapper() {
    }

    public static User mapToUser(UserDto userDto) {
        User map = modelMapper.map(userDto, User.class);
        return map;
    }

    public static UserDto mapToUserDto(User user) {
        UserDto map = modelMapper.map(user, UserDto.class);
        return map;
    }
}
