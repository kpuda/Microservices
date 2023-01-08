package com.kp.user.service.services;

import com.kp.user.service.ServerConst;
import com.kp.user.service.dto.UserDto;
import com.kp.user.service.entity.User;
import com.kp.user.service.repositories.UserRepository;
import com.kp.user.service.responses.ResponseObject;
import com.kp.user.service.responses.UserResponseObject;
import com.kp.user.service.responses.WrappedResponseObject;
import com.kp.user.service.tools.Mapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
            throw new EntityExistsException(ServerConst.USER_EXISTS_ALREADY.toString());
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
    public CompletableFuture<WrappedResponseObject> getUserOrders(Long id, HttpServletResponse response) {
        return connectionService.getOrders(id);
    }

    @CircuitBreaker(name = "order-service", fallbackMethod = "userOrderFallbackMethod")
    public CompletableFuture<WrappedResponseObject> getUserOrder(Long id, Long orderId, HttpServletResponse response) {
        return connectionService.getUserOrder(id,orderId);
    }

    public CompletableFuture<WrappedResponseObject> userOrdersFallbackMethod(Long id, HttpServletResponse response, RuntimeException runtimeException) {
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return CompletableFuture.supplyAsync(() -> new WrappedResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Oops! Something went wrong, please order after some time!",null));
    }

    public CompletableFuture<WrappedResponseObject> userOrderFallbackMethod(Long id, Long orderId, HttpServletResponse response) {
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return CompletableFuture.supplyAsync(() -> new WrappedResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Oops! Something went wrong, please order after some time!",null));
    }

}
