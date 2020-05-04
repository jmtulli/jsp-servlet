package tulli.jm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exceptionServlet")
public class ExceptionServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Integer.parseInt(request.getParameter("textField"));
      response.setStatus(200);
      response.getWriter().write("Success");
    } catch (Exception e) {
      response.setStatus(500);
      response.getWriter().write("Failure - " + e.getMessage());
    }

  }

}
