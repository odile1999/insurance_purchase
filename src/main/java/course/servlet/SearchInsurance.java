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

@WebServlet("/SearchInsurance")
public class SearchInsurance extends HttpServlet{
  protected InsuranceDao insuranceDao;

  @Override
  public void init() throws ServletException {
    insuranceDao = InsuranceDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Insurance> insurances = new ArrayList<Insurance>();
    // Retrieve and validate name.
      try {
        insurances = insuranceDao.getAllInsurance();
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    req.setAttribute("insurances", insurances);
    req.getRequestDispatcher("/SearchInsurance.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Insurance> insurances = new ArrayList<Insurance>();

    // Retrieve and validate name.
    // firstname is retrieved from the form POST submission. By default, it
    // is populated by the URL query string (in FindUsers.jsp).
    String name = req.getParameter("name");
    if (name == null || name.trim().isEmpty()) {
      messages.put("success", "Please enter a valid name.");
    } else {
      try {
    	 insurances = insuranceDao.getInsuranceByName(name);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("success", "Displaying results for " + name);
    }
    req.setAttribute("insurances", insurances);

    req.getRequestDispatcher("/SearchInsurance.jsp").forward(req, resp);
  }
}
