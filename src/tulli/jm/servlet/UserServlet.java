package tulli.jm.servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;

import tulli.jm.dao.UserDAO;
import tulli.jm.model.User;

@WebServlet("/insertUser")
@MultipartConfig
public class UserServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    UserDAO dao = new UserDAO();
    Integer id = null;
    if (request.getParameter("userid") != null && !request.getParameter("userid").isEmpty()) {
      id = Integer.valueOf(request.getParameter("userid"));
    }
    String name = request.getParameter("userName");
    String password = request.getParameter("userPassword");
    String cep = request.getParameter("userCep");
    String street = request.getParameter("userStreet");
    String city = request.getParameter("userCity");
    String userFile = request.getParameter("filename");
    User user = new User(id, name, password, cep, street, city, userFile);
    try {
      Part part = request.getPart("filename");
      if (part != null && part.getSize() > 0) {
        // Save full-size image
        // InputStream is = part.getInputStream();
        // byte[] bytes = new byte[(int) part.getSize()];
        // is.read(bytes, 0, bytes.length);
        // is.close();
        // userFile = "data:" + part.getContentType() + ";base64," +
        // Base64.encodeBase64String(bytes);

        // Save reduced image
        InputStream is = part.getInputStream();
        byte[] byteArray = new byte[(int) part.getSize()];
        is.read(byteArray, 0, byteArray.length);
        is.close();
        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(byteArray));
        int type = bufImage.getType();
        BufferedImage reduced = new BufferedImage(100, 100, type);
        Graphics2D g = reduced.createGraphics();
        g.drawImage(bufImage, 0, 0, 100, 100, null);
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(reduced, "png", out);
        userFile = "data:image/png;base64," + DatatypeConverter.printBase64Binary(out.toByteArray());
      }
    } catch (IOException | ServletException e1) {
      e1.printStackTrace();
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher("insertUser.jsp");
    if (name != null && password != null && !name.isEmpty() && !password.isEmpty()) {
      if (dao.findByName(name).getLogin() != null) {
        request.setAttribute("logMessage", "User already exists in the database.");
        request.setAttribute("user", user);
      } else {
        dao.insertUser(name, password, cep, street, city, userFile);
        request.setAttribute("logMessage", "User created in the database.");
      }
    } else {
      request.setAttribute("logMessage", "Invalid data for new user.");
    }
    try {
      request.setAttribute("userList", dao.getUserList());
      dispatcher.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    Integer id = Integer.parseInt(request.getParameter("userid"));
    UserDAO dao = new UserDAO();
    if (request.getParameter("action").equals("delete")) {
      try {
        dao.deleteUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertUser.jsp");
        request.setAttribute("userList", dao.getUserList());
        try {
          dispatcher.forward(request, response);
        } catch (Exception e) {
          e.printStackTrace();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      User user = dao.findById(id);
      String fileType = user.getFilename().split(";base64,")[0].split("/")[1];
      String file = user.getFilename().split(";base64,")[1];
      response.setHeader("Content-Disposition", "attachment;filename=file." + fileType);
      ByteArrayInputStream is = new ByteArrayInputStream(Base64.decodeBase64(file));
      try {
        ServletOutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int readSize = is.read(buffer);
        while (readSize != -1) {
          os.write(buffer, 0, readSize);
          readSize = is.read(buffer);
        }
        os.flush();
        os.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
  }

}
