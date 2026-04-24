/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.model;


/**
 *
 * @author Vanni
 */
public class Medico {
    private String nombre;
    private String especialidad;

    public Medico(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public void consultarPaciente() {
        System.out.println("Dr(a). " + nombre + " está consultando a un paciente.");
    }

    @Override
    public String toString() {
        return "Dr(a). " + nombre + " - " + especialidad;
    }
}
