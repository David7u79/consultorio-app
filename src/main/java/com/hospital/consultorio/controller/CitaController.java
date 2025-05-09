package com.hospital.consultorio.controller;

import com.hospital.consultorio.model.Cita;
import com.hospital.consultorio.DTO.CitaRequest;
import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.service.CitaService;
import com.hospital.consultorio.repository.ConsultorioRepository;
import com.hospital.consultorio.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;
    private final ConsultorioRepository consultorioRepository;
    private final DoctorRepository doctorRepository;

    // Endpoint para agendar una cita
    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody CitaRequest request) {
        try {
            Doctor doctor = doctorRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
            Consultorio consultorio = consultorioRepository.findById(request.getConsultorioId())
                    .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));

            Cita cita = Cita.builder()
                    .doctor(doctor)
                    .consultorio(consultorio)
                    .nombrePaciente(request.getNombrePaciente())
                    .horario(request.getHorario())
                    .build();

            Cita nuevaCita = citaService.agendarCita(cita);
            return ResponseEntity.ok(nuevaCita);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la cita: " + e.getMessage());
        }
    }


    // Consultar citas por doctor y fecha
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorDoctor(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(citaService.obtenerCitasPorDoctorYFecha(doctorId, fecha));
    }

    // Consultar citas por consultorio y fecha
    @GetMapping("/consultorio/{consultorioId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorConsultorio(
            @PathVariable Long consultorioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(citaService.obtenerCitasPorConsultorioYFecha(consultorioId, fecha));
    }

    // Cancelar cita (por ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarCita(@PathVariable Long id) {
        try {
            citaService.cancelarCita(id);
            return ResponseEntity.ok("Cita cancelada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCita(@PathVariable Long id, @RequestBody CitaRequest request) {
        try {


            // Recuperar el doctor y consultorio por sus IDs
            Doctor doctor = doctorRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
            Consultorio consultorio = consultorioRepository.findById(request.getConsultorioId())
                    .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));

            // Crear la nueva cita con los datos actualizados
            Cita citaEditada = new Cita();
            citaEditada.setId(id);
            citaEditada.setDoctor(doctor);
            citaEditada.setConsultorio(consultorio);
            citaEditada.setNombrePaciente(request.getNombrePaciente());
            citaEditada.setHorario(request.getHorario());

            // Usar la lógica de agendarCita para reutilizar la validación de datos
            Cita citaGuardada = citaService.agendarCita(citaEditada);  // Llamar a la misma función que usas para agendar
            return ResponseEntity.ok(citaGuardada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al editar la cita: " + e.getMessage());
        }
    }


}
