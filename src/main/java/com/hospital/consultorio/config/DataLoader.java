package com.hospital.consultorio.config;

import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.repository.ConsultorioRepository;
import com.hospital.consultorio.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final ConsultorioRepository consultorioRepository;

    public DataLoader(DoctorRepository doctorRepository, ConsultorioRepository consultorioRepository) {
        this.doctorRepository = doctorRepository;
        this.consultorioRepository = consultorioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (doctorRepository.count() == 0) {
            doctorRepository.save(Doctor.builder().nombre("Carlos").apellidoPaterno("Ramírez").apellidoMaterno("Pérez").especialidad("Medicina Interna").build());
            doctorRepository.save(Doctor.builder().nombre("María").apellidoPaterno("Gómez").apellidoMaterno("López").especialidad("Medicina Interna").build());
            doctorRepository.save(Doctor.builder().nombre("Juan").apellidoPaterno("Sánchez").apellidoMaterno("Torres").especialidad("Medicina Interna").build());
            doctorRepository.save(Doctor.builder().nombre("Laura").apellidoPaterno("Fernández").apellidoMaterno("Martínez").especialidad("Medicina Interna").build());
            doctorRepository.save(Doctor.builder().nombre("Luis").apellidoPaterno("Hernández").apellidoMaterno("Díaz").especialidad("Medicina Interna").build());
        }

        if (consultorioRepository.count() == 0) {
            consultorioRepository.save(Consultorio.builder().numero(101).piso(1).build());
            consultorioRepository.save(Consultorio.builder().numero(102).piso(1).build());
            consultorioRepository.save(Consultorio.builder().numero(201).piso(2).build());
            consultorioRepository.save(Consultorio.builder().numero(202).piso(2).build());
            consultorioRepository.save(Consultorio.builder().numero(301).piso(3).build());
        }
    }
}
