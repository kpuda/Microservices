package com.kp.user.service.tools;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import com.kp.user.service.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilityClass {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @PostConstruct
    void addUser() {
        UserDto userDto = new UserDto("James", "Blake");
        User user = mapper.mapToUser(userDto);
        userRepository.save(user);
    }
}
