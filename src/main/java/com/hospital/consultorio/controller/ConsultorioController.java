package com.hospital.consultorio.controller;

import com.hospital.consultorio.model.Consultorio;
import com.hospital.consultorio.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    // Mostrar lista de consultorios
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultorios", consultorioRepository.findAll());
        return "consultorios/lista";
    }

    // Formulario para registrar un nuevo consultorio
    @GetMapping("/nuevo")
    public String formularioConsultorio(Model model) {
        model.addAttribute("consultorio", new Consultorio());
        return "consultorios/nuevo";
    }

    // Guardar un nuevo consultorio (cuando id es null)
    @PostMapping
    public String guardar(@ModelAttribute Consultorio consultorio) {
        consultorioRepository.save(consultorio);
        return "redirect:/consultorios";
    }

    // Formulario para editar un consultorio
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Consultorio consultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consultorio no encontrado"));
        model.addAttribute("consultorio", consultorio);
        return "consultorios/nuevo";  // Usamos el mismo formulario para editar
    }

    // Actualizar un consultorio
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute Consultorio consultorio) {
        consultorio.setId(id);  // Aseguramos que el id del consultorio sea el mismo
        consultorioRepository.save(consultorio);  // Guardamos el consultorio actualizado
        return "redirect:/consultorios";
    }

    // Eliminar un consultorio
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        consultorioRepository.deleteById(id);
        return "redirect:/consultorios";
    }
}
