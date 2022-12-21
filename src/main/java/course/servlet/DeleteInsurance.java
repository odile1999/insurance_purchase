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


@WebServlet("/DeleteInsurance")
public class DeleteInsurance extends HttpServlet {

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
    // Provide a title and render the JSP.
    messages.put("title", "Delete Insurance");
    req.getRequestDispatcher("/DeleteInsurance.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate course id.
    int policyId = Integer.parseInt(req.getParameter("policyId"));
    if (policyId <= 0) {
      messages.put("title", "Invalid PolicyId");
      messages.put("disableSubmit", "true");
    } else {
      // Delete the Course.
      Insurance insurance = new Insurance(policyId);
      try {
        insurance = InsuranceDao.delete(insurance);
        // Update the message.
        if (insurance == null) {
          messages.put("title", "Successfully deleted " + policyId);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + policyId);
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/DeleteInsurance.jsp").forward(req, resp);
  }
}