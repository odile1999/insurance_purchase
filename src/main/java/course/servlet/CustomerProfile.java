package course.servlet;

import course.dal.*;
import course.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerProfile")

public class CustomerProfile extends HttpServlet {

  protected RegistrationDao registrationDao;

  @Override
  public void init() throws ServletException {
    registrationDao = RegistrationDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Registration> registrations = new ArrayList<>();
    int customerId = Integer.parseInt(req.getParameter("customerId"));
    try {
      registrations = registrationDao.getRegistrationsByUser(customerId);
      messages.put("customerId", String.valueOf(customerId));
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.setAttribute("registrations", registrations);
    req.getRequestDispatcher("/CustomerProfile.jsp").forward(req, resp);
  }

}

