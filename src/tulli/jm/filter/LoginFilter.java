package tulli.jm.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import tulli.jm.dao.DBConnection;

@WebFilter("/LoginServlet")
public class LoginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    System.out.println("vai entrar no dofilter");
    chain.doFilter(request, response);
    System.out.println("voltou do dofilter");
  }

  @Override
  public void destroy() {
    try {
      System.out.println("Closing connection.");
      DBConnection.closeConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
