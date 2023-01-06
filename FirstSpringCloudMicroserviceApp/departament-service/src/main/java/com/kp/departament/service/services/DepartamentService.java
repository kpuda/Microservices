package com.kp.departament.service.services;

import com.kp.departament.service.dto.DepartamentDto;
import com.kp.departament.service.entity.Departament;
import com.kp.departament.service.repositories.DepartamentRepository;
import com.kp.departament.service.responses.ResponseObject;
import com.kp.departament.service.responses.WrappedResponseObject;
import com.kp.departament.service.tools.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentService {

    private final DepartamentRepository departamentRepository;
    private final Mapper mapper;

    @Transactional
    public ResponseObject postDepartament(DepartamentDto departamentDto) {
        Departament user = mapper.mapToDepartament(departamentDto);
        Departament save = departamentRepository.save(user);
        return new ResponseObject(HttpStatus.CREATED.value(), "Created");
    }

    @Transactional
    public WrappedResponseObject getDepartaments() {
        List<Departament> all = departamentRepository.findAll();
        return new WrappedResponseObject(HttpStatus.OK.value(), "List of users", all);
    }
}
