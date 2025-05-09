package com.hospital.consultorio.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaRequest {
    private Long doctorId;
    private Long consultorioId;
    private String nombrePaciente;
    private LocalDateTime horario;
}