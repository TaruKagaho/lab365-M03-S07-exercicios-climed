package br.senaisc.lab365.itacorubi.semana07.climed.controllers;

import br.senaisc.lab365.itacorubi.semana07.climed.dtos.DoctorDtoRequest;
import br.senaisc.lab365.itacorubi.semana07.climed.dtos.DoctorDtoResponse;
import br.senaisc.lab365.itacorubi.semana07.climed.models.DoctorModel;
import br.senaisc.lab365.itacorubi.semana07.climed.repositories.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping("medico")
    public ResponseEntity<DoctorDtoResponse> saveDoctor(
            @RequestBody @Valid DoctorDtoRequest doctorDtoRequest
    ) {
        var doctorModel = new DoctorModel();

        BeanUtils.copyProperties(doctorDtoRequest, doctorModel);

        /*return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        doctorRepository.save(doctorModel)
                )*/
        var doctorModelSaved = doctorRepository.save(doctorModel);
        var doctorDtoResponse = new DoctorDtoResponse(doctorModelSaved);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        doctorDtoResponse
                );
    }
}
