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

  public ProductionRecord(Product product, String c) {
    int count = Integer.parseInt(c);
    date = new Date();
    System.out.println(count);
    for ( ; count > 0; count--) { //not getting in this loop
      serialNum = product.getManufacturer().substring(0, 3) + product.getType() + String.format("&05d", product.getId()) + date;
    }
    System.out.println("Serial: " + serialNum);
  }

  @Override
  public String toString() {
    return "Prod. Num: " + productionNum + " Product ID: " + productId +
        " serialNum: " + serialNum + " Date: " + date;
  }
}
