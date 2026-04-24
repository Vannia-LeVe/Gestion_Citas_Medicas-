/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.service;

import mx.itson.gestioncitasmedicas.model.Cita;
import java.util.List;
/**
 *
 * @author Vanni
 */

public interface IReporte {
    String generarReporte(List<Cita> citas, String titulo);
    default String generarReporteFormato(List<Cita> citas, String titulo, String formato) {
        return generarReporte(citas, titulo);
    }
}
