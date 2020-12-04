import java.util.Date;

public class ProductionRecord {

  int productionNum;
  int productId;
  String serialNum;
  Date date;

  public ProductionRecord(int productId) {
    productionNum = 0;
    serialNum = "0";
    date = new Date();
  }

  public ProductionRecord(int productionNum, int productId, String serialNum, Date date) {
    this.productionNum = productionNum;
    this.productId = productId;
    this.serialNum = serialNum;
    this.date = date;
  }

  public ProductionRecord(Product product, int count) {
    System.out.println("HERE");
    serialNum = product.getManufacturer().substring(0,3) + product.getType() + "00000" + date;
  }

  @Override
  public String toString() {
    return "Prod. Num: " + productionNum + " Product ID: " + productId +
        " serialNum: " + serialNum + " Date: " + date;
  }
}
