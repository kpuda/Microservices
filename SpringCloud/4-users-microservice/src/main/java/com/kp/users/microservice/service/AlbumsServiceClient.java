package com.kp.users.microservice.service;

import com.kp.users.microservice.model.AlbumResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(value = "albums-ms")
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums") //todo albumss is misspelled to check how circuitbreaker works
    @Retry(name = "albums-ms") /*,fallbackMethod = "getAlbumsFallback"*/// <- we can either create circuitbreker - or remove it and declare fallback method in retry annotation
    @CircuitBreaker(name = "albums-ms", fallbackMethod = "getAlbumsFallback")// <- fallback methods should match with name, parameters and return type. Additionally, it should have some Exception as an argument
    public List<AlbumResponseModel> getUserAlbums(@PathVariable String id);

    default List<AlbumResponseModel> getAlbumsFallback(String id, Throwable throwable) {
        System.out.println("Fallback method called");
        return new ArrayList<>();
    }

}
