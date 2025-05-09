package com.hospital.consultorio.controller;

import com.hospital.consultorio.model.Doctor;
import com.hospital.consultorio.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctores")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Mostrar lista de doctores
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("doctores", doctorRepository.findAll());
        return "doctores/lista";
    }

    // Formulario para registrar un nuevo doctor
    @GetMapping("/nuevo")
    public String formularioDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctores/nuevo";
    }

    // Guardar un nuevo doctor (cuando el id es null)
    @PostMapping
    public String guardar(@ModelAttribute Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/doctores";
    }

    // Formulario para editar un doctor
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado"));
        model.addAttribute("doctor", doctor);
        return "doctores/nuevo";  // Usamos el mismo formulario para editar
    }

    // Actualizar un doctor
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Doctor doctor) {
        doctor.setId(id);  // Aseguramos que el id del doctor sea el mismo
        doctorRepository.save(doctor);  // Guardamos el doctor actualizado
        return "redirect:/doctores";
    }

    // Eliminar un doctor
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctores";
    }
}
