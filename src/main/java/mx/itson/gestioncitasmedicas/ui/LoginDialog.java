/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.ui;

import mx.itson.gestioncitasmedicas.service.AutenticacionService;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Vanni
 */


public class LoginDialog extends JDialog {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private boolean autenticado = false;

    public LoginDialog(Frame parent) {
        super(parent, "Inicio de Sesión", true);
        setSize(350, 180);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        txtUsuario = new JTextField(15);
        panel.add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.addActionListener(e -> autenticar());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLogin);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void autenticar() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtPassword.getPassword());
        if (AutenticacionService.login(usuario, contrasena)) {
            autenticado = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
        }
    }

    public boolean isAutenticado() {
        return autenticado;
    }
}
