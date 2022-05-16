package com.bayarkhuu.visual.labs.lab14;

import com.bayarkhuu.visual.utils.Repository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfFromXmlFile {

    public static void main(String[] args) throws JRException, IOException {
        Repository<Order> repository = new Repository<>(Order.class);
        List<Order> orders = repository.findAllByCriteria(null);

        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/static/jaspers/order.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        JRDataSource dataSource = new JRBeanCollectionDataSource(orders);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        File outDir = new File("./jasperoutput");
        if (!outDir.mkdirs()) System.out.println("Folder аль хэдийн үүссэн байна.");
        JasperExportManager.exportReportToPdfFile(jasperPrint, "./jasperoutput/order.pdf");
    }
}
