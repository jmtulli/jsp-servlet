package tulli.jm.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import net.sf.jasperreports.engine.JRException;
import tulli.jm.dao.CategoryDAO;
import tulli.jm.model.Category;
import tulli.jm.report.ReportService;

@WebServlet("/datatableServlet")
public class DatatableServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    CategoryDAO dao = new CategoryDAO();
    List<Category> data = dao.listCategory();

    if (data.size() > 0) {
      StringBuilder json = new StringBuilder("{" + "  \"draw\": 1," + "  \"recordsTotal\": " + data.size() + "," + "  \"recordsFiltered\": " + data.size() + "," + "  \"data\": [");
      for (Category category : data) {
        json.append("[\"" + category.getId() + "\",\"" + category.getName() + "\"],");
      }
      json.deleteCharAt(json.length() - 1);
      json.append("] }");
      response.setStatus(200);
      response.getWriter().write(json.toString());
    } else {
      response.setStatus(204);
      response.getWriter().write("No records found");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String reportType = request.getParameter("type");
    String reportName = "Categories";

    CategoryDAO dao = new CategoryDAO();
    List<Category> data = dao.listCategory();

    if (data.size() > 0) {
      try {
        File reportFile = new ReportService().GenerateReportFile(data, request.getSession().getServletContext(), reportType, reportName);

        FileInputStream inputStream = new FileInputStream(reportFile);
        String mimeType = request.getServletContext().getMimeType(reportFile.getPath());
        response.setContentType(mimeType);
        response.setContentLength((int) reportFile.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + reportFile.getName() + "\"");

        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[4096];

        int readSize = inputStream.read(buffer);
        while (readSize != -1) {
          outputStream.write(buffer, 0, readSize);
          readSize = inputStream.read(buffer);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
        reportFile.delete();
        FileUtils.cleanDirectory(reportFile.getParentFile());
        reportFile.getParentFile().delete();
      } catch (JRException e) {
        e.printStackTrace();
      }
    }
  }
}
