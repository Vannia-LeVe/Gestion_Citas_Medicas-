/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.model;
import java.time.LocalTime;
/**
 *
 * @author Vanni
 */

public class Horario {
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Horario(String dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getDia() { return dia; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }

    public void setDia(String dia) { this.dia = dia; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public boolean incluyeHora(LocalTime hora) {
        return !hora.isBefore(horaInicio) && hora.isBefore(horaFin);
    }

    @Override
    public String toString() {
        return dia + ": " + horaInicio + " - " + horaFin;
    }
}