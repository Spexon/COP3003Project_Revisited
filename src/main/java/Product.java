public abstract class Product implements Item {

  private int id;
  private String name;
  private String manufacturer;
  private String type;

  /**
   * Products
   * id manufacturer name  type
   * 1  Apple        iPod  Audio
   * 2  Samsung      Note  Visual
   *
   * Production Records   (Get ProductId from PRODUCT in DB!)
   * productionNum productId serialNum dateProduced
   *
   * 1 1 AppAU00001 new Date()
   * 2 1 AppAU00002 new Date()
   * 3 2 SamVI00001 new Date()
   * 4 2 SamVI00002 new Date()
   * 5 1 AppAU00003 new Date()
   *
   */

  public Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString(String name, String manufacturer, String type) {

    return ("Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type);
  }

  // Getter and setter methods

  @Override
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}

// We use this class so that we can create products in other parts of our code
class Widget extends Product {

  public Widget(String name, String manufacturer, String type) {
    super(name, manufacturer, type);
  }

}


