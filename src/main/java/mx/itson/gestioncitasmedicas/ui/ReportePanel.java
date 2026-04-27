/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.gestioncitasmedicas.ui;


import mx.itson.gestioncitasmedicas.model.Cita;
import mx.itson.gestioncitasmedicas.service.CitaService;
import mx.itson.gestioncitasmedicas.service.ReporteService;
import mx.itson.gestioncitasmedicas.service.ExportadorPDF;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
/**
 *
 * @author Juan Carlos 
 */


public class ReportePanel extends JPanel {
    private CitaService citaService;
    private ReporteService reporteService;
    private ExportadorPDF exportador;
    private JComboBox<String> cmbFiltro;
    private JTextField txtValor;
    private JTextArea txtReporte;
    private String ultimoReporte;

    public ReportePanel() {
        citaService = new CitaService();
        reporteService = new ReporteService();
        exportador = new ExportadorPDF();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con filtros
        JPanel filtros = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filtros.add(new JLabel("Filtrar por:"));
        cmbFiltro = new JComboBox<>(new String[]{"Todas las citas", "Especialidad", "Médico", "Paciente"});
        filtros.add(cmbFiltro);
        filtros.add(new JLabel("Valor:"));
        txtValor = new JTextField(20);
        filtros.add(txtValor);
        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.addActionListener(e -> generarReporte());
        filtros.add(btnGenerar);
        add(filtros, BorderLayout.NORTH);

        // Área de texto para mostrar el reporte
        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        txtReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtReporte);
        add(scroll, BorderLayout.CENTER);

        // Botón exportar PDF
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnExportar = new JButton("Exportar a PDF");
        btnExportar.addActionListener(e -> exportarPDF());
        bottom.add(btnExportar);
        add(bottom, BorderLayout.SOUTH);
    }

    private void generarReporte() {
        String tipo = (String) cmbFiltro.getSelectedItem();
        String valor = txtValor.getText().trim();
        List<Cita> citas;
        String titulo;

        switch (tipo) {
            case "Especialidad":
                if (valor.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese una especialidad");
                    return;
                }
                citas = citaService.filtrarPorEspecialidad(valor);
                titulo = "Citas de especialidad: " + valor;
                break;
            case "Médico":
                if (valor.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese nombre del médico");
                    return;
                }
                citas = citaService.filtrarPorMedico(valor);
                titulo = "Citas con Dr(a). " + valor;
                break;
            case "Paciente":
                if (valor.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese nombre del paciente");
                    return;
                }
                citas = citaService.filtrarPorPaciente(valor);
                titulo = "Citas de paciente: " + valor;
                break;
            default:
                citas = citaService.getTodas();
                titulo = "Todas las citas";
                break;
        }

        ultimoReporte = reporteService.generarReporte(citas, titulo);
        txtReporte.setText(ultimoReporte);
    }

    private void exportarPDF() {
        if (ultimoReporte == null || ultimoReporte.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero genere un reporte");
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar PDF");
        chooser.setSelectedFile(new File("reporte_citas.pdf"));
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".pdf")) {
                file = new File(file.getAbsolutePath() + ".pdf");
            }
            try {
                File pdf = exportador.exportar(ultimoReporte, file.getParent());
                JOptionPane.showMessageDialog(this, "PDF guardado en:\n" + pdf.getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}