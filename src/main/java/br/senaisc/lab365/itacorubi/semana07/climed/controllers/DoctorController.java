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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("medicos")
    public ResponseEntity<List<DoctorDtoResponse>> getAllDoctors() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        doctorRepository
                                .findAll()
                                .stream()
                                .map(DoctorDtoResponse::new)
                                .toList()
                );
    }

    @PutMapping("medico/{idMedico}")
    public ResponseEntity<Object> updateDoctor(
            @PathVariable(value = "idMedico") UUID id,
            @RequestBody @Valid DoctorDtoRequest doctorDtoRequest
    ) {
        Optional<DoctorModel> doctorFound = doctorRepository.findById(id);

        if (doctorFound.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Médico não encontrado!");
        }

        var doctorModel = doctorFound.get();

        BeanUtils.copyProperties(doctorDtoRequest, doctorModel);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(doctorRepository.save(doctorModel));
    }

    @DeleteMapping("medico/{idMedico}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable(value = "idMedico") UUID id
    ) {
        boolean isDoctorFound = doctorRepository.existsById(id);

        if (!isDoctorFound) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Médico não encontrado!");
        }

        doctorRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Médico excluído com sucesso!");
    }
}
