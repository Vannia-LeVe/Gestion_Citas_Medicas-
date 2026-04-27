/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.service;
import mx.itson.gestioncitasmedicas.db.DatabaseConnection;
import java.sql.*;
/**
 *
 * @author Roberth
 */


public class AutenticacionService {

    public static boolean login(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? AND contrasena = ? AND activo = 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena); 
         
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
