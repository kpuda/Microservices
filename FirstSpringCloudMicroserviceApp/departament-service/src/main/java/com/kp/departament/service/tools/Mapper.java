package com.kp.departament.service.tools;

import com.kp.departament.service.dto.DepartamentDto;
import com.kp.departament.service.entity.Departament;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ModelMapper mapper;

    public Departament mapToDepartament(DepartamentDto departamentDto) {
        return mapper.map(departamentDto, Departament.class);
    }
}
