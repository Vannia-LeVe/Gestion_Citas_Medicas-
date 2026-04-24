/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.service;

import mx.itson.gestioncitasmedicas.model.Cita;
import mx.itson.gestioncitasmedicas.model.Horario;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Vanni
 */


public interface IGestorCitas {
    void agregarHorarioMedico(Horario horario);
    boolean programarCita(Cita cita);
    boolean confirmarCita(int indice);
    boolean cancelarCita(int indice);
    List<Cita> getTodas();
    List<Cita> filtrarPorEspecialidad(String especialidad);
    List<Cita> filtrarPorMedico(String nombreMedico);
    List<Cita> filtrarPorPaciente(String nombrePaciente);
    List<Cita> filtrarPorFecha(LocalDate fecha);
}