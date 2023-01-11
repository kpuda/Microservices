package com.kp.users.microservice.config;

import com.kp.users.microservice.filters.AuthorizationFilter;
import com.kp.users.microservice.service.CustomAuthenticationProvider;
import com.kp.users.microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final AuthorizationFilter authorizationFilter;

    @Bean
    public SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity //TODO add ip address of gateway
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests().requestMatchers("/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and().authenticationProvider(authenticationProvider())
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userService, passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

}
