package tulli.jm.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tulli.jm.dao.CategoryDAO;
import tulli.jm.dao.ProductDAO;
import tulli.jm.model.Product;

@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ProductDAO dao = new ProductDAO();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    String parameter = request.getParameter("action");
    String id = request.getParameter("productid");

    if ("delete".equals(parameter) && id != null) {
      dao.deleteProduct(Integer.parseInt(id));
      request.setAttribute("logMessage", "Product deleted");
      refreshProductList(request, response);
    } else if ("update".equals(parameter) && id != null) {
      Product product = dao.findById(Integer.parseInt(id));
      request.setAttribute("product", product);
      CategoryDAO categoryDao = new CategoryDAO();
      request.setAttribute("categories", categoryDao.listCategory());
      request.setAttribute("msg", "Edit product:");
      try {
        request.getRequestDispatcher("editProduct.jsp").forward(request, response);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      refreshProductList(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String parameter = request.getParameter("action");
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String amount = request.getParameter("amount");
    String price = request.getParameter("price");
    String categoryId = request.getParameter("categoryId");
    if (!"refresh".equals(parameter) && id != null && !id.isEmpty() && name != null && amount != null && price != null && categoryId != null && !name.isEmpty() && !amount.isEmpty() && !price.isEmpty() && !categoryId.isEmpty()) {
      dao.updateProduct(Integer.parseInt(id), name, Integer.parseInt(amount), Double.parseDouble(price), Integer.parseInt(categoryId));
      request.setAttribute("logMessage", "Product updated");
      refreshProductList(request, response);
    } else if ("insert".equals(parameter)) {
      try {
        request.setAttribute("msg", "Add product:");
        CategoryDAO categoryDao = new CategoryDAO();
        request.setAttribute("categories", categoryDao.listCategory());
        request.getRequestDispatcher("editProduct.jsp").forward(request, response);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (!"refresh".equals(parameter) && id.isEmpty() && name != null && amount != null && price != null && categoryId != null && !name.isEmpty() && !amount.isEmpty() && !price.isEmpty() && !categoryId.isEmpty()) {
      dao.insertProduct(name, Integer.parseInt(amount), Double.parseDouble(price), Integer.parseInt(categoryId));
      request.setAttribute("logMessage", "Product created");
      refreshProductList(request, response);
    } else {
      refreshProductList(request, response);
    }
  }

  private void refreshProductList(HttpServletRequest request, HttpServletResponse response) {
    request.setAttribute("productList", dao.getProductList());
    CategoryDAO categoryDao = new CategoryDAO();
    request.setAttribute("categories", categoryDao.listCategory());
    try {
      request.getRequestDispatcher("productList.jsp").forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
