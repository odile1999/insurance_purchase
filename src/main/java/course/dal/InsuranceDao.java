package course.dal;

import course.model.Insurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class InsuranceDao {

  // Single pattern: instantiation is limited to one object.
  private static course.dal.InsuranceDao instance = null;
  /**
   * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
   * instance. This is used to store {@link Insurance} into your MySQL instance and retrieve
   * {@link Insurance} from MySQL instance.
   */
  protected static ConnectionManager connectionManager;

  protected InsuranceDao() {
    connectionManager = new ConnectionManager();
  }

  public static course.dal.InsuranceDao getInstance() {
    if (instance == null) {
      instance = new course.dal.InsuranceDao();
    }
    return instance;
  }

  public Insurance create(Insurance insurance) throws SQLException {
    String insertInsurance =
        "INSERT INTO Insurance(Name, Premium, Deductible, OutOfPocketMax, Description) "
            +"VALUES(?, ?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertInsurance,
          Statement.RETURN_GENERATED_KEYS);

      insertStmt.setString(1, insurance.getName());
      insertStmt.setInt(2, insurance.getPremium());
      insertStmt.setInt(3, insurance.getDeductible());
      insertStmt.setInt(4, insurance.getOutOfPocketMax());
      insertStmt.setString(5, insurance.getDescription());
      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int policyId = -1;
      if (resultKey.next()) {
        policyId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      insurance.setPolicyId(policyId);
      return insurance;
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

  /**
   * Delete the Insurance instance. This runs a DELETE statement.
   */
  public static Insurance delete(Insurance insurance) throws SQLException {
    String deleteInsurance = "DELETE FROM Insurance WHERE PolicyId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteInsurance);
      deleteStmt.setInt(1, insurance.getPolicyId());
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

  public Insurance updateDescription(Insurance insurance, String newDescription) throws SQLException {
    String updateInsurance = "UPDATE Insurance SET Description=? WHERE PolicyId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateInsurance);
      updateStmt.setString(1, newDescription);
      updateStmt.setInt(2, insurance.getPolicyId());
      updateStmt.executeUpdate();

      // Update param before returning to the caller.
      insurance.setDescription(newDescription);
      return insurance;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public Insurance getInsuranceFromPolicyId(int policyId) throws SQLException {
    String getInsurance =
        "SELECT PolicyId, Name, Premium, Deductible, OutOfPocketMax, Description "
            + "FROM Insurance WHERE PolicyId = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getInsurance);
      selectStmt.setInt(1, policyId);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String name = results.getString("Name");
        int premium = results.getInt("Premium");
        int deductible = results.getInt("Deductible");
        int outOfPocketMax = results.getInt("OutOfPocketMax");
        String description = results.getString("Description");
        return new Insurance(policyId, name, premium, deductible, outOfPocketMax, description);
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

  public List<Insurance> getInsuranceByName(String name) throws SQLException {
    List<Insurance> insurances = new ArrayList<>();
    String getInsurance =
        "SELECT PolicyId, Name, Premium, Deductible, OutOfPocketMax, Description "
            + "FROM Insurance "
            + "WHERE Name =?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getInsurance);
      selectStmt.setString(1, name);
      results = selectStmt.executeQuery();

      while (results.next()) {
        int policyId = results.getInt("PolicyId");
        int premium = results.getInt("Premium");
        int deductible = results.getInt("Deductible");
        int outOfPocketMax = results.getInt("OutOfPocketMax");
        String description = results.getString("Description");

        Insurance insurance = new Insurance(policyId, name, premium, deductible, outOfPocketMax, description);
        insurances.add(insurance);
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
    return insurances;
  }

  public List<Insurance> getAllInsurance() throws SQLException {
    List<Insurance> insurances = new ArrayList<>();
    String getInsurance =
        "SELECT PolicyId, Name, Premium, Deductible, OutOfPocketMax, Description "
            + "FROM Insurance ";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(getInsurance);
      results = selectStmt.executeQuery();

      while (results.next()) {
        int policyId = results.getInt("PolicyId");
        String name = results.getString("Name");
        int premium = results.getInt("Premium");
        int deductible = results.getInt("Deductible");
        int outOfPocketMax = results.getInt("OutOfPocketMax");
        String description = results.getString("Description");

        Insurance insurance = new Insurance(policyId, name, premium, deductible, outOfPocketMax, description);
        insurances.add(insurance);
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
    return insurances;
  }
}

