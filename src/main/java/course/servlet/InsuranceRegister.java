package course.servlet;

import course.dal.*;
import course.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsuranceRegister")
public class InsuranceRegister extends HttpServlet {
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
    // Provide a title and render the JSP.
    messages.put("title", "Register for a Insurance");
    req.getRequestDispatcher("/InsuranceRegister.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    int customerId = Integer.parseInt(req.getParameter("customerId"));
    int policyId = Integer.parseInt(req.getParameter("policyId"));
    try {
      Registration registration = new Registration(policyId, customerId);
      registration = registrationDao.create(registration);
      messages.put("success", "Successfully created " + registration.getRegistrationId());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("/InsuranceRegister.jsp").forward(req, resp);
  }

}
