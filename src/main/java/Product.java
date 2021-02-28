public abstract class Product implements Item {

  private int id;
  private String name;
  private String manufacturer;
  private ItemType type;

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

  public Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString() {

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
  public ItemType getType() {
    return type;
  }


  @Override
  public String getManufacturer() {
    return manufacturer;
  }


  @Override
  public String getName() {
    return name;
  }

}

// We use this class so that we can create products in other parts of our code
class Widget extends Product {

  public Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }


}


