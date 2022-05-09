package com.bayarkhuu.visual.labs.lab14;

import com.bayarkhuu.visual.labs.lab9.Repository;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

        Repository<Order> repository = new Repository<>(Order.class);

        List<Order> orders = Arrays.asList(
                new Order("Аяга", 1, 1000D, "Улаанбаатар 1"),
                new Order("Ундаа", 3, 2000, "Улаанбаатар 2"),
                new Order("Халбага", 6, 50000, "Улаанбаатар 3"),
                new Order("Сэрээ", 9, 1500, "Улаанбаатар 4"),
                new Order("Таваг", 11, 1600, "Улаанбаатар 5"),
                new Order("Хайрцаг", 17, 1100, "Улаанбаатар 6"),
                new Order("Бээлий", 20, 100, "Улаанбаатар 7"),
                new Order("Цамц", 60, 18000, "Улаанбаатар 8"),
                new Order("Өмд", 69, 10700, "Улаанбаатар 9")
        );

        for (Order order : orders) {
            repository.save(order);
        }
    }
}
