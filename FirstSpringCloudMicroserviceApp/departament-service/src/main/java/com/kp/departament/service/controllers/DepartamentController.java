package com.kp.departament.service.controllers;

import com.kp.departament.service.dto.DepartamentDto;
import com.kp.departament.service.responses.ResponseObject;
import com.kp.departament.service.responses.WrappedResponseObject;
import com.kp.departament.service.services.DepartamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departament")
@RequiredArgsConstructor
public class DepartamentController {

    private final DepartamentService departamentService;

    @PostMapping
    public ResponseObject postUser(@RequestBody DepartamentDto departamentDto) {
        return departamentService.postDepartament(departamentDto);
    }

    @GetMapping("/all")
    public WrappedResponseObject getUsers() {
        return departamentService.getDepartaments();
    }
}
