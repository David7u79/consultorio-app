package com.hospital.consultorio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePaciente;

    private LocalDateTime horario;

    @ManyToOne
    @JoinColumn(name = "doctor_Id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "consultorio_Id")
    private Consultorio consultorio;
}
