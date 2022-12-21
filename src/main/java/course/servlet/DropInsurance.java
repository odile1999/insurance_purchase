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

@WebServlet("/DropInsurance")

public class DropInsurance extends HttpServlet {

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

    int registraionId = Integer.parseInt(req.getParameter("record"));

    
    try {
      // Registration registration = registrationDao.getRegistraionFromRegistrationId();
      Registration registration = new Registration(registraionId);
      registration = registrationDao.drop(registration);
      messages.put("success", "Successfully dropped Insurance");
      
      
      
      req.setAttribute("registration", registration);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.getRequestDispatcher("/DropInsurance.jsp").forward(req, resp);
  }

}


