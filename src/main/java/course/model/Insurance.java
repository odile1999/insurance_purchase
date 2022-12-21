package course.model;

public class Insurance{

  protected String name;
  protected int policyId;
  protected int premium;
  protected int deductible;
  protected int outOfPocketMax;
  protected String description;

  public Insurance() {
  }

  public Insurance(int policyId, String name, int premium, int deductible, int outOfPocketMax,
      String description) {
    this.policyId = policyId;
    this.name = name;
    this.premium = premium;
    this.deductible = deductible;
    this.outOfPocketMax = outOfPocketMax;
    this.description = description;
  }

  public Insurance(int policyId2){
    this.policyId = policyId2;
  }

  public Insurance(String name, int premium, int deductible, int outOfPocketMax, String description) {
    this.name = name;
    this.premium = premium;
    this.deductible = deductible;
    this.outOfPocketMax = outOfPocketMax;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPolicyId() {
    return policyId;
  }

  public void setPolicyId(int policyId) {
    this.policyId = policyId;
  }

  public int getPremium() {
    return premium;
  }

  public void setPremium(int premium) {
    this.premium = premium;
  }

  public int getDeductible() {
    return deductible;
  }

  public void setDeductible(int deductible) {
    this.deductible = deductible;
  }

  public int getOutOfPocketMax() {
    return outOfPocketMax;
  }

  public void setOutOfPocketMax(int outOfPocketMax) {
    this.outOfPocketMax = outOfPocketMax;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}