/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas;

import javax.swing.SwingUtilities;
import mx.itson.gestioncitasmedicas.ui.LoginDialog;
import mx.itson.gestioncitasmedicas.ui.MainFrame;

/**
 *
 * @author Roberth
 */

public class MainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginDialog login = new LoginDialog(null);
            login.setVisible(true);
            if (login.isAutenticado()) {
                new MainFrame().setVisible(true);
            } else {
                System.exit(0);
            }
        });
    }
}