/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.service;

import mx.itson.gestioncitasmedicas.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Vanni
 */



public class CitaService implements IGestorCitas{
    private List<Cita> citas = new ArrayList<>();
    private List<Horario> horariosMedicos = new ArrayList<>();

    public void agregarHorarioMedico(Horario horario) {
        horariosMedicos.add(horario);
    }

    public boolean programarCita(Cita cita) {
        // Validar que la fecha no sea pasada
        if (cita.getFecha().isBefore(LocalDate.now())) {
            return false;
        }
        
        // Validar que no haya otra cita a la misma hora con el mismo médico
        boolean conflicto = citas.stream().anyMatch(c -> 
            c.getMedico().equals(cita.getMedico()) &&
            c.getFecha().equals(cita.getFecha()) &&
            c.getHora().equals(cita.getHora())
        );
        
        if (conflicto) {
            return false;
        }
        
        citas.add(cita);
        cita.getPaciente().agendarCita();
        return true;
    }

    public boolean confirmarCita(int indice) {
        if (indice >= 0 && indice < citas.size()) {
            citas.get(indice).confirmar();
            return true;
        }
        return false;
    }

    public boolean cancelarCita(int indice) {
        if (indice >= 0 && indice < citas.size()) {
            citas.remove(indice);
            return true;
        }
        return false;
    }

    public List<Cita> getTodas() {
        return new ArrayList<>(citas);
    }

    public List<Cita> filtrarPorEspecialidad(String especialidad) {
        return citas.stream()
            .filter(c -> c.getMedico().getEspecialidad().equalsIgnoreCase(especialidad))
            .collect(Collectors.toList());
    }

    public List<Cita> filtrarPorMedico(String nombreMedico) {
        return citas.stream()
            .filter(c -> c.getMedico().getNombre().equalsIgnoreCase(nombreMedico))
            .collect(Collectors.toList());
    }

    public List<Cita> filtrarPorPaciente(String nombrePaciente) {
        return citas.stream()
            .filter(c -> c.getPaciente().getNombre().equalsIgnoreCase(nombrePaciente))
            .collect(Collectors.toList());
    }

    public List<Cita> filtrarPorFecha(LocalDate fecha) {
        return citas.stream()
            .filter(c -> c.getFecha().equals(fecha))
            .collect(Collectors.toList());
    }
}
