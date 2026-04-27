/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.ui;
import javax.swing.*;

/**
 *
 * @author Juan Carlos 
 */


public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Sistema de Gestión de Citas Médicas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        add(new ReportePanel());
    }
}