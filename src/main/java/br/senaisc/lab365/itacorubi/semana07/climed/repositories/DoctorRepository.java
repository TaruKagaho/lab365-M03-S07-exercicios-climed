package br.senaisc.lab365.itacorubi.semana07.climed.repositories;

import br.senaisc.lab365.itacorubi.semana07.climed.models.DoctorModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel, UUID> {
}
