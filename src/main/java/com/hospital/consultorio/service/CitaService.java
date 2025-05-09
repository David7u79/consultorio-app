package com.hospital.consultorio.service;

import com.hospital.consultorio.model.Cita;
import com.hospital.consultorio.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;

    public Cita agendarCita(Cita cita) throws Exception {
        LocalDateTime inicio = cita.getHorario().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime fin = inicio.plusHours(1);

        // Regla 1: No se puede agendar en el mismo consultorio a la misma hora
        List<Cita> citasConsultorio = citaRepository
                .findByConsultorioIdAndHorarioBetween(cita.getConsultorio().getId(), inicio, fin);
        if (!citasConsultorio.isEmpty()) {
            throw new Exception("Ya hay una cita en ese consultorio a esa hora.");
        }

        // Regla 2: No se puede agendar para el mismo doctor a la misma hora
        List<Cita> citasDoctor = citaRepository
                .findByDoctorIdAndHorarioBetween(cita.getDoctor().getId(), inicio, fin);
        if (!citasDoctor.isEmpty()) {
            throw new Exception("El doctor ya tiene una cita a esa hora.");
        }

        // Regla 3: El paciente no puede tener otra cita el mismo día con menos de 2 horas de diferencia
        LocalDate fecha = cita.getHorario().toLocalDate();
        LocalDateTime startDay = fecha.atStartOfDay();
        LocalDateTime endDay = fecha.atTime(LocalTime.MAX);
        List<Cita> citasPaciente = citaRepository
                .findByNombrePacienteAndHorarioBetween(cita.getNombrePaciente(), startDay, endDay);

        for (Cita c : citasPaciente) {
            long diff = Math.abs(c.getHorario().until(cita.getHorario(), java.time.temporal.ChronoUnit.HOURS));
            if (diff < 2) {
                throw new Exception("El paciente ya tiene una cita muy cercana el mismo día.");
            }
        }

        // Regla 4: El doctor no puede tener más de 8 citas al día
        List<Cita> citasDelDoctorHoy = citaRepository
                .findByDoctorIdAndHorarioBetween(cita.getDoctor().getId(), startDay, endDay);
        if (citasDelDoctorHoy.size() >= 8) {
            throw new Exception("El doctor ya tiene 8 citas ese día.");
        }

        // Si pasa todas las validaciones, se guarda
        return citaRepository.save(cita);
    }

    public List<Cita> obtenerCitasPorDoctorYFecha(Long doctorId, LocalDate fecha) {
        LocalDateTime start = fecha.atStartOfDay();
        LocalDateTime end = fecha.atTime(LocalTime.MAX);
        return citaRepository.findByDoctorIdAndHorarioBetween(doctorId, start, end);
    }

    public List<Cita> obtenerCitasPorConsultorioYFecha(Long consultorioId, LocalDate fecha) {
        LocalDateTime start = fecha.atStartOfDay();
        LocalDateTime end = fecha.atTime(LocalTime.MAX);
        return citaRepository.findByConsultorioIdAndHorarioBetween(consultorioId, start, end);
    }

    public void cancelarCita(Long id) throws Exception {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new Exception("Cita no encontrada"));

        if (cita.getHorario().isBefore(LocalDateTime.now())) {
            throw new Exception("No se puede cancelar una cita pasada.");
        }

        citaRepository.delete(cita);
    }

    public Cita editarCita(Cita cita) throws Exception {
        if (!citaRepository.existsById(cita.getId())) {
            throw new Exception("Cita no encontrada para editar.");
        }
        // Reutiliza la lógica de validación como si fuera una nueva cita
        return agendarCita(cita);
    }


}
