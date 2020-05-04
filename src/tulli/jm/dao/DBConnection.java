package tulli.jm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  static Connection connection;
  private static String url = "jdbc:mysql://localhost/jsp?useSSL=false&allowPublicKeyRetrieval=true&useTimezone=true&serverTimezone=UTC";
  private static String user = "root";
  private static String password = "root";

  static {
    try {
      System.out.println("Connecting to DB");
      connection = DriverManager.getConnection(url, user, password);
      connection.setAutoCommit(false);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    return connection;
  }

  public static void closeConnection() throws SQLException {
    connection.close();
  }

}
