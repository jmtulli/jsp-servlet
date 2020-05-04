package tulli.jm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tulli.jm.model.User;

public class UserDAO {

  private Connection connection;

  public User findByName(String name) {
    connection = DBConnection.getConnection();
    String query = "select * from user where name = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, name);
      ps.execute();
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
        return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
      } else {
        return new User();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new User();
  }

  public User findById(int id) {
    connection = DBConnection.getConnection();
    String query = "select * from user where userid = ? and name <> 'admin'";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, id);
      ps.execute();
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
        return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
      } else {
        return new User();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new User();
  }

  public void insertUser(String name, String password, String cep, String street, String city, String userFile) {
    connection = DBConnection.getConnection();
    String query = "insert into user (name, password, cep, street, city, userfile) values (?, ?, ?, ?, ?, ?)";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, name);
      ps.setString(2, password);
      ps.setString(3, cep);
      ps.setString(4, street);
      ps.setString(5, city);
      ps.setString(6, userFile);
      ps.execute();
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
  }

  public void updateUser(int id, String name, String password, String cep, String street, String city, String userFile) {
    connection = DBConnection.getConnection();
    String query = "update user set name = ?, password = ?, cep = ?, street = ?, city = ?, userfile = ? where userid = ? and name <> 'admin'";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, name);
      ps.setString(2, password);
      ps.setInt(6, id);
      ps.setString(3, cep);
      ps.setString(4, street);
      ps.setString(5, city);
      ps.setString(6, userFile);
      ps.execute();
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
  }

  public void deleteUser(int userId) {
    connection = DBConnection.getConnection();
    String query = "delete from user where userid = ? and name <> 'admin'";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, userId);
      ps.execute();
      connection.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }
  }

  public List<User> getUserList() {
    List<User> users = new ArrayList<>();
    connection = DBConnection.getConnection();

    String query = "select * from user where name <> 'admin' order by name asc";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.execute();
      ResultSet rs = ps.getResultSet();
      while (rs.next()) {
        users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      connection.rollback();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return users;
  }

}
