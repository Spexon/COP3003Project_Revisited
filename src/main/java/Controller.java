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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;

public class Controller {

  @FXML private ListView<Product> prodListView;
  @FXML private TableView<Product> existingProdTable;
  @FXML private TableColumn<Product, String> nameCol;
  @FXML private TableColumn<Product, String> manuCol;
  @FXML private TableColumn<Product, String> typeCol;
  @FXML private ComboBox<Integer> produceComboBox;
  @FXML private ChoiceBox<ItemType> typeChoiceBox;
  @FXML private TextField manufactField;
  @FXML private TextField prodNameField;
  ObservableList<Product> data = FXCollections.observableArrayList();
  ObservableList<ItemType> items = FXCollections.observableArrayList();
  ObservableList<Integer> nums = FXCollections.observableArrayList();

  public void addProduct() {
    connectProductDB();
    populateListView();
    System.out.println("Product Added!");
  }

  public void recordProduction() {
    if (!(produceComboBox.getSelectionModel().getSelectedItem() == null)) {
    //if (!(produceComboBox.getSelectionModel().getSelectedItem() == null)) {
      ArrayList<ProductionRecord> productionRun = new ArrayList<>();
      System.out.println("Produce: " + produceComboBox.getSelectionModel().getSelectedItem() + "\n"
          + prodListView.getSelectionModel().getSelectedItem().getName());
      ProductionRecord prod = new ProductionRecord(prodListView.getSelectionModel().getSelectedItem(),
          produceComboBox.getSelectionModel().getSelectedIndex());

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
    items.add(ItemType.AUDIO);
    items.add(ItemType.VISUAL);
    items.add(ItemType.AUDIO_MOBILE);
    items.add(ItemType.VISUAL_MOBILE);

    typeChoiceBox.getSelectionModel().selectFirst();
    typeChoiceBox.setItems(items);
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
      pstmt.setString(2, typeChoiceBox.getValue().toString());
      pstmt.setString(3, manufactField.getText());

      pstmt.executeUpdate();

      Widget prod = new Widget(prodNameField.getText(), typeChoiceBox.getValue().toString(),manufactField.getText());
      data.add(prod);
      existingProdTable.getItems().addAll(prod);

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
      while (rs.next()) {
        Widget prod = new Widget(rs.getString(2),rs.getString(4),rs.getString(3));
        data.add(prod);
      }
      existingProdTable.getItems().addAll(data);

      stmt.close();
      conn.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Populates the listView in produce tab when program launches, I believe
   * there should be a way to use Product's .toString() method here.
   */
  public void populateListView() {
  prodListView.getItems().clear();

    Product dataList;
    for (int i = 0; i < data.size(); i++) {
      dataList = new Widget(data.get(i).getName(), data.get(i).getManufacturer(), data.get(i).getType());
       prodListView.getItems().addAll(dataList);
    }
  }

  public void setUpProductLine() {
    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    manuCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));


    prodListView.setItems(data);
  }

  public void initialize() {

    populateChoiceBox();
    populateComboBox();

    setUpProductLine();
    populateTableView();
    populateListView();



  }
}
