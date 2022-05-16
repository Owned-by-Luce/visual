package com.bayarkhuu.visual.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperUtil {

    public static <T> JasperPrint create(String jrxml, String outPath, List<T> data) {
        JasperReport jasperReport;
        try {
            jasperReport = JasperCompileManager.compileReport(jrxml);
            Map<String, Object> parameters = new HashMap<>();
            JRDataSource dataSource = new JRBeanCollectionDataSource(data);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            File outDir = new File("./jasperoutput");
            outDir.mkdirs();
            JasperExportManager.exportReportToHtmlFile(jasperPrint, outPath);
            return jasperPrint;
        } catch (JRException e) {
            e.printStackTrace();
            return null;
        }
    }
}
