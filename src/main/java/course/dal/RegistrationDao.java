package course.dal;

import course.model.Registration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDao {
  private static RegistrationDao instance = null;
  protected static ConnectionManager connectionManager;

  protected RegistrationDao() {
    connectionManager = new ConnectionManager();
  }

  public static RegistrationDao getInstance() {
    if (instance == null) {
      instance = new RegistrationDao();
    }
    return instance;
  }

  public Registration create(Registration registration) throws SQLException {
    String insertRegistration =
        "INSERT INTO Registration(PolicyId, CustomerId)"
            +"VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertRegistration,
          Statement.RETURN_GENERATED_KEYS);

      insertStmt.setInt(1, registration.getPolicyId());
      insertStmt.setInt(2, registration.getCustomerId());
      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int registrationId = -1;
      if (resultKey.next()) {
        registrationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      registration.setRegistrationId(registrationId);
      return registration;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Registration drop(Registration registration) throws SQLException {
    String deleteRegistration = "DELETE FROM Registration WHERE RegistrationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRegistration);
      deleteStmt.setInt(1, registration.getRegistrationId());
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  public Registration getRegistraionFromRegistrationId(int registrationId) throws SQLException {
    String getRegistration =
        "SELECT * FROM Registration WHERE RegistrationId = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getRegistration);
      selectStmt.setInt(1, registrationId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        int policyId = results.getInt("PolicyId");
        int customerId = results.getInt("CustomerId");
        return new Registration(registrationId, policyId, customerId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
    }
    return null;
  }

  public List<Registration> getRegistrationsByUser(int customerId) throws SQLException {
    List<Registration> registrations = new ArrayList<>();
    String getRegistration =
        "SELECT Registration.RegistrationId, Registration.PolicyId, Name "
            + "FROM Registration "
            + "INNER JOIN Insurance I on Registration.PolicyId = I.PolicyId "
            + "WHERE CustomerId = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getRegistration);
      selectStmt.setInt(1, customerId);
      results = selectStmt.executeQuery();

      while (results.next()) {
        int registrationId = results.getInt("Registration.RegistrationId");
        int policyId = results.getInt("Registration.PolicyId");
        String insuranceName = results.getString("Name");
        Registration registration = new Registration(registrationId, policyId, insuranceName);
        registrations.add(registration);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return registrations;
  }

}
