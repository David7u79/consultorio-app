package com.hospital.consultorio.controller;


import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioRestController {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @GetMapping
    public List<Consultorio> listarConsultorios() {
        return consultorioRepository.findAll();
    }
}
