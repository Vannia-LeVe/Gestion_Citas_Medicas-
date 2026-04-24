/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.model;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Vanni
 */

public class Cita {
    private LocalDate fecha;
    private LocalTime hora;
    private boolean confirmada;
    private Paciente paciente;
    private Medico medico;

    public Cita(LocalDate fecha, LocalTime hora, Paciente paciente, Medico medico) {
        this.fecha = fecha;
        this.hora = hora;
        this.paciente = paciente;
        this.medico = medico;
        this.confirmada = false;
    }

    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public boolean isConfirmada() { return confirmada; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public void confirmar() {
        this.confirmada = true;
        System.out.println("Cita confirmada para " + paciente.getNombre());
    }

    @Override
    public String toString() {
        return fecha + " " + hora + " - " + paciente.getNombre() + 
               " con " + medico.getNombre() + 
               (confirmada ? " [Confirmada]" : " [Pendiente]");
    }
}