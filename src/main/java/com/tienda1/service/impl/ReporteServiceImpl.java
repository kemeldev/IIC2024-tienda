package com.tienda1.service.impl;

import com.tienda1.service.ReporteService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReporteServiceImpl implements ReporteService {

    // para poder acceder a la base de datos y asi generar el reporte
    @Autowired
    DataSource datasource;
    
    @Override
    public ResponseEntity<Resource> generaReporte(
            String reporte,
            Map<String, Object> parametros,
            String tipo) throws IOException {
        try {

            String estilo;
            if ("vPdf".equals(tipo)) {
                estilo = "inline; ";
            } else {
                estilo = "attachment; ";
            }

            // nombre del folder donde estan los archivos .jasper
            String reportePath = "reportes";

            // el objeto donde queda el reporte generado en memoria
            ByteArrayOutputStream salida = new ByteArrayOutputStream();


            // se define el objeto desde donde se lee el archivo .jasper
            ClassPathResource fuente
                    = new ClassPathResource(
                            reportePath
                            + File.separator
                            + reporte
                            + ".jasper");            

            // El objeto donde efectivametne se lee la informacion 
            InputStream elReporte = fuente.getInputStream();


            // el objeto donde se crea el reporte ya generado
            var reporteJasper
                    = JasperFillManager
                            .fillReport(
                                    elReporte,
                                    parametros,
                                    datasource.getConnection());
            
            // el objeto para generar la respuesta Html
            MediaType mediaType = null;
            
            String archivoSalida = ""; // archivo de salida en texto del reporte
            
            byte[] data; //utilzado para repsonder al usuario el reporte
            
            // determinacion de que tipo de reporte se desea
            if (null != tipo) switch (tipo) {
                case "Pdf", "vPdf" -> {
                    JasperExportManager.exportReportToPdfStream(reporteJasper, salida);
                    mediaType = MediaType.APPLICATION_PDF;
                    archivoSalida = reporte + ".pdf";
                }
                case "Xls" -> {
                    JRXlsxExporter exportador = new JRXlsxExporter();
                    exportador.setExporterInput(
                            new SimpleExporterInput(
                                    reporteJasper));
                    exportador.setExporterOutput(
                            new SimpleOutputStreamExporterOutput(
                                    salida));
                    
                    SimpleXlsxReportConfiguration configuracion=
                            new SimpleXlsxReportConfiguration();
                    
                    configuracion.setDetectCellType(true);
                    configuracion.setCollapseRowSpan(true);
                    exportador.setConfiguration(configuracion);
                    exportador.exportReport();
                    mediaType = MediaType.APPLICATION_OCTET_STREAM;
                    archivoSalida = reporte + ".xlsx";
                }
                case "Csv" -> {
                    JRCsvExporter exportador = new JRCsvExporter();
                    exportador.setExporterInput(
                            new SimpleExporterInput(
                                    reporteJasper));
                    exportador.setExporterOutput(
                            new SimpleWriterExporterOutput(
                                    salida));
                    exportador.exportReport();
                    mediaType = MediaType.TEXT_PLAIN;
                    archivoSalida = reporte + ".csv";
                    }
                default -> {
                    }
            }

            // en data se conviert el reporte de cualquier tipo en un arreglo de bytes
            data = salida.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Disposition",
                    estilo + "filename=\"" + archivoSalida + "\"");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(data.length)
                    .contentType(mediaType)
                    .body(
                            new InputStreamResource(
                                    new ByteArrayInputStream(data)));

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            return null;
        }

    }
}
