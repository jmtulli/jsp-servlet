package tulli.jm.report;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class ReportService {

  public File GenerateReportFile(List<?> data, ServletContext context, String reportType, String reportName) throws JRException, IOException {
    String tmpFolderPath = System.getProperty("java.io.tmpdir") + File.separator + "jasperReport";
    File tmpFolder = new File(tmpFolderPath);
    if (!tmpFolder.exists()) {
      tmpFolder.mkdirs();
    }

    File jasperFile = new File(tmpFolderPath + File.separator + reportName + ".jasper");
    if (!jasperFile.exists()) {
      InputStream stream = context.getResourceAsStream("/WEB-INF/jasper/" + reportName + ".jrxml");
      JasperDesign jasperDesign = JRXmlLoader.load(stream);
      JasperCompileManager.compileReportToFile(jasperDesign, tmpFolderPath + File.separator + reportName + ".jasper");
      stream.close();
    }

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
    String file = JasperFillManager.fillReportToFile(jasperFile.getPath(), null, dataSource);
    jasperFile.delete();

    if ("pdf".equals(reportType)) {
      JasperExportManager.exportReportToPdfFile(file, tmpFolderPath + File.separator + reportName + ".pdf");
    } else if ("html".equals(reportType)) {
      JasperExportManager.exportReportToHtmlFile(file, tmpFolderPath + File.separator + reportName + ".html");
    } else if ("xls".equals(reportType)) {
      // deprecated:
      // ex.setParameter(JRExporterParameter.INPUT_FILE_NAME, file);
      // ex.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, tmpFolderPath + File.separator +
      // "Categories.xls");
      // replaced by:
      JRXlsExporter ex = new JRXlsExporter();
      ex.setExporterInput(new SimpleExporterInput(file));
      ex.setExporterOutput(new SimpleOutputStreamExporterOutput(tmpFolderPath + File.separator + reportName + ".xls"));

      SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
      configuration.setOnePagePerSheet(true);
      configuration.setDetectCellType(true);
      configuration.setCollapseRowSpan(false);

      ex.setConfiguration(configuration);
      ex.exportReport();
    }

    File reportFile = new File(tmpFolderPath + File.separator + reportName + "." + reportType);
    return reportFile;

    // @formatter:off
    /*
    JasperReport report = null;
    String file = null;
    JRBeanCollectionDataSource ds = null;
    String jasperPath = "D:\\Dev\\Java\\jsp-servlet\\WebContent\\WEB-INF\\jasper\\Categories.";
    try {
      report = JasperCompileManager.compileReport(jasperPath + "jrxml");
      Map param = new HashMap();
      param.put("TYPE", "pdf");
      ds = new JRBeanCollectionDataSource(data);
      file = JasperFillManager.fillReportToFile(jasperPath + "jasper", param, ds);
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
    */
 // @formatter:on
  }

}
