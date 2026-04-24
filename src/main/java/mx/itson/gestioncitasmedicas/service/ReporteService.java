/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.service;


import mx.itson.gestioncitasmedicas.model.Cita;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 *
 * @author Vanni
 */
// ReporteService.java


public class ReporteService implements IReporte {

    public String generarReporte(List<Cita> citas, String titulo) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("=".repeat(60)).append("\n");
        sb.append("  SISTEMA DE CITAS MÉDICAS\n");
        sb.append("  ").append(titulo).append("\n");
        sb.append("=".repeat(60)).append("\n");
        sb.append("Generado: ").append(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        ).append("\n");
        sb.append("=".repeat(60)).append("\n\n");

        if (citas.isEmpty()) {
            sb.append("No hay citas registradas.\n");
        } else {
            sb.append(String.format("%-12s %-8s %-20s %-15s %s\n",
                "Fecha", "Hora", "Médico", "Paciente", "Estado"));
            sb.append("-".repeat(60)).append("\n");

            for (Cita c : citas) {
                sb.append(String.format("%-12s %-8s %-20s %-15s %s\n",
                    c.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    c.getHora().format(DateTimeFormatter.ofPattern("HH:mm")),
                    acortar(c.getMedico().getNombre(), 20),
                    acortar(c.getPaciente().getNombre(), 15),
                    c.isConfirmada() ? "Confirmada" : "Pendiente"));
            }
            
            sb.append("-".repeat(60)).append("\n");
            sb.append("Total: ").append(citas.size()).append(" cita(s)\n");
        }
        
        sb.append("\n").append("=".repeat(60)).append("\n");
        
        return sb.toString();
    }

    private String acortar(String texto, int max) {
        if (texto.length() <= max) return texto;
        return texto.substring(0, max - 3) + "...";
    }
}