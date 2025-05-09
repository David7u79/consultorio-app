package com.hospital.consultorio.controller;


import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorRestController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> listarDoctores() {
        return doctorRepository.findAll();
    }
}
