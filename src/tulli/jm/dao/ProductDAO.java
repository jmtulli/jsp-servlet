package tulli.jm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tulli.jm.model.Product;

public class ProductDAO {

  public List<Product> getProductList() {
    List<Product> list = new ArrayList<>();
    Connection connection = DBConnection.getConnection();
    String query = "select * from product order by name asc";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.execute();
      ResultSet rs = ps.getResultSet();
      while (rs.next()) {
        list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("amount"), rs.getDouble("price"), rs.getInt("categoryid")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return list;
  }

  public void deleteProduct(int id) {
    Connection connection = DBConnection.getConnection();
    String query = "delete from product where id = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, id);
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

  public void updateProduct(int id, String name, int amount, double price, int categoryId) {
    Connection connection = DBConnection.getConnection();
    String query = "update product set name=?, amount=?, price=?, categoryid=? where id=?";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, name);
      ps.setInt(2, amount);
      ps.setDouble(3, price);
      ps.setInt(4, categoryId);
      ps.setInt(5, id);
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

  public void insertProduct(String name, int amount, double price, int categoryId) {
    Connection connection = DBConnection.getConnection();
    String query = "insert into product (name, amount, price, categoryid) values (?, ?, ?, ?)";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setString(1, name);
      ps.setInt(2, amount);
      ps.setDouble(3, price);
      ps.setInt(4, categoryId);
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

  public Product findById(int id) {
    Product product = new Product();
    Connection connection = DBConnection.getConnection();
    String query = "select * from product where id = ?";
    try {
      PreparedStatement ps = connection.prepareStatement(query);
      ps.setInt(1, id);
      ps.execute();
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
        return (new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("amount"), rs.getDouble("price"), rs.getInt("categoryid")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return product;
  }

}
