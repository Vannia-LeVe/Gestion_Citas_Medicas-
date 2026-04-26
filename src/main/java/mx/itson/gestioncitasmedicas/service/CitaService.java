/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.service;

import mx.itson.gestioncitasmedicas.model.*;
import mx.itson.gestioncitasmedicas.db.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vanni
 */

public class CitaService implements IGestorCitas {

    // ----- CONSULTAS -----
    public List<Cita> getTodas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.fecha, c.hora, c.confirmada, " +
                     "p.nombre AS paciente_nombre, p.edad, p.telefono, " +
                     "m.nombre AS medico_nombre, e.nombre AS especialidad_nombre " +
                     "FROM cita c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id " +
                     "JOIN especialidad e ON m.especialidad_id = e.id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                citas.add(mapResultSetToCita(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public List<Cita> filtrarPorEspecialidad(String especialidad) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.fecha, c.hora, c.confirmada, " +
                     "p.nombre AS paciente_nombre, p.edad, p.telefono, " +
                     "m.nombre AS medico_nombre, e.nombre AS especialidad_nombre " +
                     "FROM cita c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id " +
                     "JOIN especialidad e ON m.especialidad_id = e.id " +
                     "WHERE e.nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, especialidad);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                citas.add(mapResultSetToCita(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public List<Cita> filtrarPorMedico(String nombreMedico) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.fecha, c.hora, c.confirmada, " +
                     "p.nombre AS paciente_nombre, p.edad, p.telefono, " +
                     "m.nombre AS medico_nombre, e.nombre AS especialidad_nombre " +
                     "FROM cita c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id " +
                     "JOIN especialidad e ON m.especialidad_id = e.id " +
                     "WHERE m.nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreMedico);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                citas.add(mapResultSetToCita(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public List<Cita> filtrarPorPaciente(String nombrePaciente) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.fecha, c.hora, c.confirmada, " +
                     "p.nombre AS paciente_nombre, p.edad, p.telefono, " +
                     "m.nombre AS medico_nombre, e.nombre AS especialidad_nombre " +
                     "FROM cita c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id " +
                     "JOIN especialidad e ON m.especialidad_id = e.id " +
                     "WHERE p.nombre = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombrePaciente);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                citas.add(mapResultSetToCita(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public List<Cita> filtrarPorFecha(LocalDate fecha) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT c.fecha, c.hora, c.confirmada, " +
                     "p.nombre AS paciente_nombre, p.edad, p.telefono, " +
                     "m.nombre AS medico_nombre, e.nombre AS especialidad_nombre " +
                     "FROM cita c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id " +
                     "JOIN especialidad e ON m.especialidad_id = e.id " +
                     "WHERE c.fecha = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(fecha));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                citas.add(mapResultSetToCita(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }
        private Cita mapResultSetToCita(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente(
            rs.getString("paciente_nombre"),
            rs.getInt("edad"),
            rs.getString("telefono")
        );
        Especialidad especialidad = new Especialidad(rs.getString("especialidad_nombre"), "");
        Medico medico = new Medico(rs.getString("medico_nombre"), especialidad.getNombre());
        LocalDate fecha = rs.getDate("fecha").toLocalDate();
        LocalTime hora = rs.getTime("hora").toLocalTime();
        Cita cita = new Cita(fecha, hora, paciente, medico);
        cita.setConfirmada(rs.getBoolean("confirmada")); // 
        return cita;
    }

    @Override
    public void agregarHorarioMedico(Horario horario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean programarCita(Cita cita) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean confirmarCita(int indice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean cancelarCita(int indice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
