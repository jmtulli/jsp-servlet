package tulli.jm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tulli.jm.model.User;

@WebFilter({"/access.jsp", "/exceptions.jsp"})
public class AccessFilter implements Filter {

  public void destroy() {}

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    System.out.println("access filter");
    HttpSession session = ((HttpServletRequest) request).getSession();
    User user = (User) session.getAttribute("logUser");
    if (user!= null) System.out.println("logUser: " + user.getLogin());
    if (user != null && user.isValidUser(user.getLogin(), user.getPassword())) {
      chain.doFilter(request, response);
    } else {
      RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
      dispatcher.forward(request, response);
    }
  }

  public void init(FilterConfig fConfig) throws ServletException {}

}
