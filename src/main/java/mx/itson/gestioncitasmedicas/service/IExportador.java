/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package mx.itson.gestioncitasmedicas.service;

import java.io.File;
/**
 *
 * @author Vanni
 */


public interface IExportador {
    File exportar(String contenido, String rutaDirectorio);
    default File exportarConNombre(String contenido, String rutaDirectorio, String nombreArchivo) {
        return exportar(contenido, rutaDirectorio);
    }
    default String getExtension() {
        return ".txt";
    }
}