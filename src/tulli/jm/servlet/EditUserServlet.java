package tulli.jm.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tulli.jm.dao.UserDAO;
import tulli.jm.model.User;

@WebServlet("/updateUser")
public class EditUserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    UserDAO dao = new UserDAO();
    RequestDispatcher dispatcher = request.getRequestDispatcher("updateUser.jsp");
    if (request.getParameter("action").equals("update")) {
      try {
        Integer id = Integer.parseInt(request.getParameter("userid"));
        User user = dao.findById(id);
        request.setAttribute("user", user);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    try {
      dispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    UserDAO dao = new UserDAO();
    Integer id = Integer.parseInt(request.getParameter("userId"));
    String name = request.getParameter("userName");
    String password = request.getParameter("userPassword");
    String cep = request.getParameter("userCep");
    String street = request.getParameter("userStreet");
    String city = request.getParameter("userCity");
    String userFile = request.getParameter("userFile");

    RequestDispatcher dispatcher = request.getRequestDispatcher("insertUser.jsp");
    if (!"cancel".equals(request.getParameter("action"))) {
      User user = dao.findByName(name);
      if (user.getLogin() != null && !user.getId().equals(id)) {
        request.setAttribute("logMessage", "User name already exists in the database.");
      } else {
        dao.updateUser(id, name, password, cep, street, city, userFile);
        request.setAttribute("logMessage", "User information updated.");
      }
    }
    try {
      request.setAttribute("userList", dao.getUserList());
      dispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
