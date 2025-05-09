package com.hospital.consultorio.repository;

import com.hospital.consultorio.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByDoctorIdAndHorarioBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    List<Cita> findByConsultorioIdAndHorarioBetween(Long consultorioId, LocalDateTime start, LocalDateTime end);
    List<Cita> findByNombrePacienteAndHorarioBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);
}
