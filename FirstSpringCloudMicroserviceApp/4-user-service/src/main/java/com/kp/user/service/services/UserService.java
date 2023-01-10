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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    public WrappedResponseObject getUserOrders(Long id, HttpServletResponse response) {
        ResponseEntity<WrappedResponseObject> responseEntity = null;
        try {
            responseEntity = connectionService.getOrders(id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (responseEntity == null) {
            throw new EntityNotFoundException(ServerConst.LIST_IS_EMPTY.getMessage());
        }
        log.info("User order response from ORDER-SERVICE: " + responseEntity.getBody().toString());
        return responseEntity.getBody();
    }

    @CircuitBreaker(name = "order-service", fallbackMethod = "userOrderFallbackMethod")
    public WrappedResponseObject getUserOrder(Long id, Long orderId, HttpServletResponse response) {
        WrappedResponseObject userOrder = null;
        try {
            userOrder = connectionService.getUserOrder(id, orderId).get();
        } catch (InterruptedException e) {
//            todo handleException
        } catch (ExecutionException e) {
//            todo handleException
        }
        if (userOrder == null) {
            throw new EntityNotFoundException(ServerConst.LIST_IS_EMPTY.getMessage());
        }
        log.info("User order response from ORDER-SERVICE: " + userOrder);
        return userOrder;
    }

    /* FALLBACK METHODS */
    public CompletableFuture<WrappedResponseObject> userOrdersFallbackMethod(Long id, HttpServletResponse response, RuntimeException runtimeException) {
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return CompletableFuture.supplyAsync(() -> new WrappedResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Oops! Something went wrong, please order after some time!", null));
    }

    public WrappedResponseObject userOrderFallbackMethod(Long id, Long orderId, HttpServletResponse response, RuntimeException runtimeException) {
        response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        return new WrappedResponseObject(HttpStatus.SERVICE_UNAVAILABLE.value(), "Oops! Something went wrong, please order after some time!", null);
    }

}
