package tulli.jm.service;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class ReportService {

  public static void GenerateReport(List<?> data) {
    System.out.println("data size " + data.size());
    JasperReport report = null;
    String file = null;
    JRBeanCollectionDataSource ds = null;
    String jasperPath = "D:\\Dev\\Java\\jsp-servlet\\src\\tulli\\jm\\report\\Categories.";
    try {
      report = JasperCompileManager.compileReport(jasperPath + "jrxml");
      ds = new JRBeanCollectionDataSource(data);
      file = JasperFillManager.fillReportToFile(jasperPath + "jasper", null, ds);
      if (file != null) {
        // Print file using system dialog
        // JasperPrintManager.printReport(file, true);

        JasperExportManager.exportReportToPdfFile(file, jasperPath + "pdf");
        JasperExportManager.exportReportToHtmlFile(file, jasperPath + "html");
        JRXlsExporter ex = new JRXlsExporter();
        ex.setParameter(JRExporterParameter.INPUT_FILE_NAME, file);
        ex.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, jasperPath + "xls");
        ex.exportReport();
      }
    } catch (JRException e) {
      e.printStackTrace();
    }
    System.out.println("compilado " + report);
    System.out.println("ds " + ds);
    System.out.println("file " + file);
  }

}
