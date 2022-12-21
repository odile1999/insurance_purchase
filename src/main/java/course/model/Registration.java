package course.model;

public class Registration {
 protected int registrationId;
 protected int policyId;
 protected String insuranceName;
 protected int customerId;

  public Registration(int registrationId, int policyId, String insuranceName, int customerId) {
    this.registrationId = registrationId;
    this.policyId = policyId;
    this.insuranceName = insuranceName;
    this.customerId = customerId;
  }

  public Registration(int registrationId) {
    this.registrationId = registrationId;
  }

  public Registration(int policyId, int customerId) {
    this.policyId = policyId;
    this.customerId = customerId;
  }

  public Registration(int registrationId, int policyId, int customerId) {
    this.registrationId = registrationId;
    this.policyId = policyId;
    this.customerId = customerId;
  }

  public Registration(int registrationId, int policyId, String insuranceName) {
    this.registrationId = registrationId;
    this.policyId = policyId;
    this.insuranceName = insuranceName;
  }

  public Registration(int policyId, String insuranceName, int customerId) {
    this.policyId = policyId;
    this.insuranceName = insuranceName;
    this.customerId = customerId;
  }

  public int getRegistrationId() {
    return registrationId;
  }

  public void setRegistrationId(int registrationId) {
    this.registrationId = registrationId;
  }

  public int getPolicyId() {
    return policyId;
  }

  public void setPolicyId(int policyId) {
    this.policyId = policyId;
  }

  public String getInsuranceName() {
    return insuranceName;
  }

  public void setInsuranceName(String insuranceName) {
    this.insuranceName = insuranceName;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
}
