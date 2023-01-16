package com.kp.users.microservice.service;

import com.kp.users.microservice.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "albums-ms")
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albumss") //todo albumss is misspelled to check how circuitbreaker works
    public List<AlbumResponseModel> getUserAlbums(@PathVariable String id);

}
