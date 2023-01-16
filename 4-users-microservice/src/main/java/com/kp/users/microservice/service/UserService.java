package com.kp.users.microservice.service;

import com.kp.users.microservice.dto.UserDto;
import com.kp.users.microservice.entity.UserEntity;
import com.kp.users.microservice.model.AlbumResponseModel;
import com.kp.users.microservice.model.ResponseModel;
import com.kp.users.microservice.model.UserResponseModel;
import com.kp.users.microservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AlbumsServiceClient albumsClient;
    Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    public UserService(ModelMapper modelMapper, UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, AlbumsServiceClient albumsClient) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.albumsClient = albumsClient;
    }

    public ResponseEntity createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(HttpStatus.CREATED.value(), "User created"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = null;
        user = userRepository.findByEmail(username);
        if (user != null) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        } else {
            throw new BadCredentialsException("BAD_CREADENTIALS");
        }
    }

    public ResponseEntity<UserResponseModel> getUser(String userId) {
        UserEntity byId = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException("Not found"));
        UserDto userDto = modelMapper.map(byId, UserDto.class);
        List<AlbumResponseModel> albumResponseModels = albumsClient.getUserAlbums(String.valueOf(byId.getId()));
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setAlbums(albumResponseModels);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseModel);
    }
}
