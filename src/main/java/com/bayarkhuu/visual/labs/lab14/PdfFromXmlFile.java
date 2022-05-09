package com.bayarkhuu.visual.labs.lab14;

import net.sf.jasperreports.engine.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfFromXmlFile {

    public static void main(String[] args) throws JRException, IOException {
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/static/jaspers/StyledTextReport/StyledTextReport.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        JRDataSource dataSource = new JREmptyDataSource();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        File outDir = new File("./jasperoutput");
        if (!outDir.mkdirs()) System.out.println("Folder аль хэдийн үүссэн байна.");
        JasperExportManager.exportReportToPdfFile(jasperPrint, "./jasperoutput/StyledTextReport.pdf");
    }
}
