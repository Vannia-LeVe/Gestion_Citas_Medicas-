
package mx.itson.gestioncitasmedicas.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileNotFoundException;

public class ExportadorPDF implements IExportador {

    
    public File exportar(String contenido, String rutaDirectorio) {
        // Asegurar que el directorio existe
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        // Crear nombre de archivo único
        String nombreArchivo = "reporte_citas_" + 
            java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            ) + ".pdf";
        
        File archivoPDF = new File(directorio, nombreArchivo);
        
        try {
            PdfWriter writer = new PdfWriter(archivoPDF.getAbsolutePath());
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Agregar el contenido línea por línea
            String[] lineas = contenido.split("\n");
            for (String linea : lineas) {
                document.add(new Paragraph(linea));
            }

            document.close();
            return archivoPDF;
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error al crear el PDF: " + e.getMessage(), e);
        }
    }
    
    @Override
    public String getExtension() {
        return ".pdf";
    }
}