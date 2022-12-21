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


@WebServlet("/UpdateInsurance")
public class UpdateInsurance extends HttpServlet {

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

    // Retrieve user and validate.
    int policyId = Integer.parseInt(req.getParameter("policyId"));
    if (policyId <= 0) {
      messages.put("Success", "Invalid PolicyId");
    } else {
      try {
        Insurance insurance = insuranceDao.getInsuranceFromPolicyId(policyId);
        req.setAttribute("insurance", insurance);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UpdateInsurance.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    int policyId = Integer.parseInt(req.getParameter("policyId"));
    if (policyId <= 0) {
      messages.put("Success", "Invalid CourseId");
    } else {
      try {
        Insurance insurance = insuranceDao.getInsuranceFromPolicyId(policyId);
        if(insurance == null) {
          messages.put("success", "CourseId does not exist. No update to perform.");
        } else {
          String newDescription = req.getParameter("insuranceDescription");
          if (newDescription == null || newDescription.trim().isEmpty()) {
            messages.put("success", "Please enter a valid description.");
          } else {
            insurance = insuranceDao.updateDescription(insurance, newDescription);
            messages.put("success", "Successfully updated " + policyId);
          }
        }
        req.setAttribute("insurance", insurance);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UpdateInsurance.jsp").forward(req, resp);
  }
}