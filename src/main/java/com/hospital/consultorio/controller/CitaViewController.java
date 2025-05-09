package com.hospital.consultorio.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CitaViewController {

    @GetMapping("/citas/gestion")
    public String mostrarVistaGestion() {
        return "citas/gestion"; // Esto carga templates/citas/gestion.html
    }
}
