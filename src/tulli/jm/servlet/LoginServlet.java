package tulli.jm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tulli.jm.dao.UserDAO;
import tulli.jm.model.User;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    UserDAO dao = new UserDAO();
    User user = dao.findByName(request.getParameter("login"));

    try {
      if (user.isValidUser(request.getParameter("login"), request.getParameter("password"))) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertUser.jsp");
        request.setAttribute("userList", dao.getUserList());
        request.getSession().setAttribute("logUser", user);
        dispatcher.forward(request, response);
      } else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("accessDenied.jsp");
        dispatcher.forward(request, response);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if ("logoff".equals(request.getParameter("action"))) {
      System.out.println("invalidate");
      request.getSession().invalidate();
      RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
      dispatcher.forward(request, response);
    }
  }

}
