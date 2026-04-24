/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mx.itson.gestioncitasmedicas;
import mx.itson.gestioncitasmedicas.model.*;
import mx.itson.gestioncitasmedicas.service.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
/**
 *
 * @author Vanni
 */
public class Main {
    
    // Dependencias ahora declaradas como interfaces (DIP cumplido)
    private static IGestorCitas citaService = new CitaService();
    private static IReporte reporteService = new ReporteService();
    private static IExportador exportador = new ExportadorPDF();
    
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Medico> medicos = new ArrayList<>();
    private static String ultimoReporte = "";
    
    public static void main(String[] args) {
        inicializarDatos();
        
        Scanner scanner = new Scanner(System.in);
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
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción inválida");
            }
        } while(opcion != 0);
    }
    
    private static void inicializarDatos() {
        Especialidad cardiologia = new Especialidad("Cardiología", "Enfermedades del corazón");
        Especialidad pediatria = new Especialidad("Pediatría", "Atención infantil");
        Especialidad dermatologia = new Especialidad("Dermatología", "Enfermedades de la piel");
        
        medicos.add(new Medico("Carlos García", "Cardiología"));
        medicos.add(new Medico("Ana López", "Pediatría"));
        medicos.add(new Medico("Laura Martínez", "Dermatología"));
        
        pacientes.add(new Paciente("María González", 30, "5512345678"));
        pacientes.add(new Paciente("Juan Pérez", 25, "5523456789"));
        pacientes.add(new Paciente("Luis Niño", 8, "5534567890"));
        
        Cita cita1 = new Cita(
            LocalDate.now().plusDays(1),
            LocalTime.of(9, 0),
            pacientes.get(0),
            medicos.get(0)
        );
        citaService.programarCita(cita1);
        
        Cita cita2 = new Cita(
            LocalDate.now().plusDays(2),
            LocalTime.of(11, 30),
            pacientes.get(2),
            medicos.get(1)
        );
        citaService.programarCita(cita2);
        citaService.confirmarCita(1);  // confirmar la segunda cita (índice 1)
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