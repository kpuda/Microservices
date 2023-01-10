package com.kp.user.service.services;

import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import com.kp.user.service.repositories.UserRepository;
import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.UserResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import com.kp.user.service.tools.Mapper;
import com.kp.user.service.tools.ServerConst;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ConnectionService connectionService;
    private final Mapper mapper;

    @Transactional
    public ResponseObject postUser(UserDto userDto) {
        Optional<User> byFirstNameAndLastName = userRepository.findByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName());
        if (byFirstNameAndLastName.isPresent()) {
            throw new EntityExistsException(ServerConst.USER_EXISTS_ALREADY.name());
        }
        userRepository.save(mapper.mapToUser(userDto));
        return new ResponseObject(HttpStatus.CREATED.value(), "Created");
    }

    public UserResponseObject getUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        UserDto userDto = mapper.maptoUserDto(byId.get());
        return new UserResponseObject(HttpStatus.OK.value(), "User found", userDto);
    }


    @CircuitBreaker(name = "order-service", fallbackMethod = "userOrdersFallbackMethod")
    public ResponseEntity<?> getUserOrders(Long id, HttpServletResponse response) {
        ResponseEntity<WrappedResponseObject> responseEntity = connectionService.getOrders(id);
        if (responseEntity.getBody() != null) {
            log.info("User order list response from ORDER-SERVICE: " + responseEntity.getBody().toString());
        } else {
            log.info("User order list is empty. Response payload" + responseEntity.toString());
        }
        return responseEntity;
    }

    @CircuitBreaker(name = "order-service", fallbackMethod = "userOrderFallbackMethod")
    public ResponseEntity<?> getUserOrder(Long id, Long orderId) {
        ResponseEntity<WrappedResponseObject> responseEntity = connectionService.getUserOrder(id, orderId);
        if (responseEntity.getBody() != null) {
            log.info("User order response from ORDER-SERVICE: " + responseEntity.getBody().toString());
        } else {
            log.info("User order list is empty. Response payload" + responseEntity.toString());
        }
        return responseEntity;
    }

    /* FALLBACK METHODS */
    public ResponseEntity<?> userOrdersFallbackMethod(Long id, HttpServletResponse response, RuntimeException runtimeException) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> userOrderFallbackMethod(Long id, Long orderId, RuntimeException runtimeException) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
