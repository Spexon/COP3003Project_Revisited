import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

  @FXML public TextArea prodLogDisplay;
  @FXML private ListView<Product> prodListView;
  @FXML private TableView<Product> existingProdTable;
  @FXML private TableColumn<Product, String> nameCol;
  @FXML private TableColumn<Product, String> manuCol;
  @FXML private TableColumn<Product, String> typeCol;
  @FXML private ComboBox<Integer> produceComboBox;
  //@FXML private ChoiceBox<ItemType> typeChoiceBox; // make sure to change FXML id if switching back to this (causes NPE)
  @FXML private ChoiceBox<String> typeChoiceBoxStr;
  @FXML private TextField manufactField;
  @FXML private TextField prodNameField;
  final ObservableList<Product> data = FXCollections.observableArrayList();
  ObservableList<String> items = FXCollections.observableArrayList();
  ObservableList<Integer> nums = FXCollections.observableArrayList();

  public void addProduct() {
    connectProductDB();
    System.out.println("Product Added!");
  }

  public void populateTextArea(ProductionRecord prod) {
    //prodLogDisplay.appendText(prod);
  }

  public void recordProduction() {
    if (!(produceComboBox.getSelectionModel().getSelectedItem() == null)) {

      ArrayList<ProductionRecord> productionRun = new ArrayList<>();
      String count = String.valueOf(produceComboBox.getValue()); // if i don't convert to string, Invocation errors will occur
      //int count = produceComboBox.getValue();
      ProductionRecord prod = new ProductionRecord(prodListView.getSelectionModel().getSelectedItem(), // this is the issue
          count);

      System.out.println("Produce: " + produceComboBox.getSelectionModel().getSelectedItem() + "\n"
          + prodListView.getSelectionModel().getSelectedItem().getName());
      System.out.println(prod);
    }
    else {
      System.out.println("Please select a quantity!");
    }
  }

  public void populateComboBox() {

    for (int i = 1; i <= 10; i++) {
      nums.add(i);
    }
    produceComboBox.getSelectionModel().selectFirst();
    produceComboBox.setItems(nums);
  }

  public void populateChoiceBox() {
    for (ItemType item : ItemType.values()) {

      //items.add(String.valueOf(item));
      typeChoiceBoxStr.getItems().add(String.valueOf(item));
    }

    typeChoiceBoxStr.getSelectionModel().selectFirst();
    //typeChoiceBoxStr.setItems(items);
  }

  public void connectProductDB() {

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/HR";

    //  Database credentials

    final String USER = "";
    final String PASS = "testPWD";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      String SQL = "INSERT INTO PRODUCT (name, type, manufacturer) VALUES (?,?,?)";

      pstmt = conn.prepareStatement(SQL);

      pstmt.setString(1, prodNameField.getText());
      pstmt.setString(2, typeChoiceBoxStr.getValue());
      pstmt.setString(3, manufactField.getText());

      pstmt.executeUpdate();

      Widget prod = new Widget(prodNameField.getText(), manufactField.getText(), ItemType.valueOf(typeChoiceBoxStr.getValue()));
      data.add(prod);

      // STEP 4: Clean-up environment
      pstmt.close();
      conn.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void populateTableView() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./resources/HR";

    //  Database credentials

    final String USER = "";
    final String PASS = "testPWD";

    Connection conn = null;
    Statement stmt = null;

    try {

      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();

      //STEP 3: Execute a query
      String SQL = "SELECT * FROM PRODUCT";

      ResultSet rs = stmt.executeQuery(SQL);

      data.clear(); // avoid duplication
      String name;
      String manufacturer;
      ItemType type;

      while (rs.next()) {
        name = rs.getString(2);
        manufacturer = rs.getString(4);

        type = null;

        for (ItemType item : ItemType.values()) {
          if (String.valueOf(item).equals(rs.getString(3))) {
            type = item;
          }
        }

        Widget prod = new Widget(name,manufacturer,type);
        data.add(prod);
      }

      stmt.close();
      conn.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void setUpProductLine() {
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    manuCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Adds products to tableview
    existingProdTable.setItems(data);
    prodListView.setItems(data);
  }

  public void initialize() {

    populateChoiceBox();
    populateComboBox();

    setUpProductLine();
    populateTableView();
  }
}
