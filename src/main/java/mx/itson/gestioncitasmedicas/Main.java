/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mx.itson.gestioncitasmedicas;

import mx.itson.gestioncitasmedicas.model.*;
import mx.itson.gestioncitasmedicas.service.*;
import java.io.File;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Vanni
 */


public class Main {

    private static IGestorCitas citaService = new CitaService();
    private static IReporte reporteService = new ReporteService();
    private static IExportador exportador = new ExportadorPDF();
    private static String ultimoReporte = "";
    private static String usuarioActual = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // --- LOGIN ---
        System.out.println("=== INICIO DE SESIÓN ===");
        boolean autenticado = false;
        while (!autenticado) {
            System.out.print("Usuario: ");
            String user = scanner.nextLine();
            System.out.print("Contraseña: ");
            String pass = scanner.nextLine();
            
            if (AutenticacionService.login(user, pass)) {
                usuarioActual = user;
                autenticado = true;
                System.out.println("Bienvenido " + user + "\n");
            } else {
                System.out.println("Credenciales incorrectas. Intente de nuevo.\n");
            }
        }
        
        // --- MENÚ---
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE CITAS MÉDICAS ===");
            System.out.println("1. Generar reporte");
            System.out.println("2. Exportar a PDF");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch(opcion) {
                case 1: generarReporte(scanner); break;
                case 2: exportarPDF(scanner); break;
                case 0: System.out.println("¡Hasta luego, " + usuarioActual + "!"); break;
                default: System.out.println("Opción inválida");
            }
        } while(opcion != 0);
        
        scanner.close();
    }
    
    private static void generarReporte(Scanner scanner) {
        System.out.println("\n--- GENERAR REPORTE ---");
        System.out.println("1. Todas las citas");
        System.out.println("2. Por especialidad");
        System.out.println("3. Por médico");
        System.out.println("4. Por paciente");
        System.out.print("Opción: ");
        
        int op = scanner.nextInt();
        scanner.nextLine();
        
        List<Cita> citasFiltradas;
        String titulo;
        
        switch(op) {
            case 1:
                citasFiltradas = citaService.getTodas();
                titulo = "Todas las Citas";
                break;
            case 2:
                System.out.print("Especialidad: ");
                String esp = scanner.nextLine();
                citasFiltradas = citaService.filtrarPorEspecialidad(esp);
                titulo = "Citas de " + esp;
                break;
            case 3:
                System.out.print("Nombre del médico: ");
                String med = scanner.nextLine();
                citasFiltradas = citaService.filtrarPorMedico(med);
                titulo = "Citas con Dr(a). " + med;
                break;
            case 4:
                System.out.print("Nombre del paciente: ");
                String pac = scanner.nextLine();
                citasFiltradas = citaService.filtrarPorPaciente(pac);
                titulo = "Citas de " + pac;
                break;
            default:
                System.out.println("Opción inválida");
                return;
        }
        
        ultimoReporte = reporteService.generarReporte(citasFiltradas, titulo);
        System.out.println("\n" + ultimoReporte);
    }
    
    private static void exportarPDF(Scanner scanner) {
        if (ultimoReporte.isEmpty()) {
            System.out.println("Primero genere un reporte");
            return;
        }
        
        System.out.print("Ruta de destino (ej. C:\\reportes): ");
        String ruta = scanner.nextLine();
        
        try {
            File pdf = exportador.exportar(ultimoReporte, ruta);
            System.out.println("✓ Archivo exportado: " + pdf.getAbsolutePath());
        } catch(Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
}
    
 
