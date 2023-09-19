package br.senaisc.lab365.itacorubi.semana07.climed.dtos;

import br.senaisc.lab365.itacorubi.semana07.climed.annotations.ValidUuid;

import br.senaisc.lab365.itacorubi.semana07.climed.models.DoctorModel;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record DoctorDto(
        @NotBlank(message = "O campo nome é obrigatório!")
        @ValidUuid
        UUID id,

        @NotBlank(message = "O campo nome é obrigatório!")
        String nome,

        @NotBlank(message = "O campo email é obrigatório!")
        @Email(message = "O campo email deve ser um endereço de e-mail válido!")
        String email,

        @NotBlank(message = "O campo telefone é obrigatório!")
        @Size(min = 16, max = 16, message = "O campo telefone teve está neste formato: (99) 9 9999-9999.")
        String telefone,

        @NotBlank(message = "O campo cpf é obrigatório!")
        String crm
) {
        public DoctorDto(DoctorModel doctorModel) {
                this(
                        doctorModel.getId(),
                        doctorModel.getNome(),
                        doctorModel.getEmail(),
                        doctorModel.getTelefone(),
                        doctorModel.getCrm()
                );
        }
}
