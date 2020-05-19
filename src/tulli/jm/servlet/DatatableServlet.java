package tulli.jm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tulli.jm.dao.CategoryDAO;
import tulli.jm.model.Category;
import tulli.jm.service.ReportService;

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
    System.out.println("dopost");
    String parameter = request.getParameter("action");
    System.out.println("param " + parameter);

    CategoryDAO dao = new CategoryDAO();
    List<Category> data = dao.listCategory();

    if (data.size() > 0) {
      ReportService.GenerateReport(data);
    }
  }
}
