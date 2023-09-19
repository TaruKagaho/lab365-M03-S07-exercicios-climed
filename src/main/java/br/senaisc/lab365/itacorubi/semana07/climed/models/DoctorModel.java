package br.senaisc.lab365.itacorubi.semana07.climed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "medicos")
@Getter
@Setter
public class DoctorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String telefone;

    private String email;

    private String crm;
}
