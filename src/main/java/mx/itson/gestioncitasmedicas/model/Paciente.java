/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.itson.gestioncitasmedicas.model;


/**
 *
 * @author Vanni
 */


public class Paciente {
    private String nombre;
    private int edad;
    private String telefono;

    public Paciente(String nombre, int edad, String telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public void agendarCita() {
        System.out.println(nombre + " ha agendado una cita.");
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años, Tel: " + telefono + ")";
    }
}