package tulli.jm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tulli.jm.model.Category;

public class CategoryDAO {

  public List<Category> listCategory() {
    List<Category> categories = new ArrayList<>();
    Connection connection = DBConnection.getConnection();
    String sql = "select * from category order by name asc";
    try {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.execute();
      ResultSet rs = ps.getResultSet();
      while (rs.next()) {
        categories.add(new Category(rs.getInt("id"), rs.getString("name")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return categories;
  }

}
