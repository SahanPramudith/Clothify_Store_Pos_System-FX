package controller.itemController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.supplierController.SupplierController;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import model.Supplier;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {

    private Parent parent;
    private Stage stage;
    private EventObject actionEvent;
    private Scene scene;
    @FXML
    private JFXComboBox<String> cmdSupplierId;

    @FXML
    private TableColumn<?, ?> colBynigPrice;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSellingPrice;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private TableColumn<?, ?> colcategorie;

    @FXML
    private JFXTextField lblBynigPrice;

    @FXML
    private JFXTextField lblItemCode;

    @FXML
    private JFXTextField lblItemName;

    @FXML
    private JFXTextField lblQty;

    @FXML
    private JFXTextField lblSellingPrice;

    @FXML
    private JFXTextField lblSize;

    @FXML
    private JFXTextField lblSupplierName;

    @FXML
    private JFXComboBox<String> lblcategorie;

    @FXML
    private TableView<Item> tblItem;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSupplierId();
        reloard();
        id();
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        colBynigPrice.setCellValueFactory(new PropertyValueFactory<>("bynigPrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, item, newval) -> {
            if (newval!=null){
                addValueTotext(newval);
            }
        });

        ObservableList<String> catagory = FXCollections.observableArrayList();
        catagory.add("Ladies");
        catagory.add("Gents");
        catagory.add("Kids");
        lblcategorie.setItems(catagory);


        cmdSupplierId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if (t1!=null){
                searsupplierid(t1);
                System.out.println("val"+t1);
            }
        });

    }

    private void searsupplierid(String id) {
        System.out.println("id = " + id);
        Supplier supplier = SupplierController.getInstance().SearchSupplier( id);
        lblSupplierName.setText(supplier.getSupname());
       // System.out.println("Checking nulls: supplierID = " + supplier + ", itemCode = " + supplier.getSupname());

    }

    private void addValueTotext(Item newval) {
        lblItemCode.setText(newval.getItemcode());
        lblSize.setText(newval.getSize());
        lblQty.setText(String.valueOf(newval.getQty()));
        lblSellingPrice.setText(""+newval.getSellingPrice());
        lblBynigPrice.setText(""+newval.getBynigPrice());
        lblItemName.setText(newval.getItemname());

    }

    ItemService service=ItemController.getInstance() ;
    @FXML
    void btnAddOnAction(ActionEvent event) {

        Item item = new Item(
                lblItemCode.getText(),
                cmdSupplierId.getValue(),
                lblItemName.getText(),
                lblSupplierName.getText(),
                lblSize.getText(),
                lblcategorie.getValue(),
                Double.valueOf(lblBynigPrice.getText()),
                Double.valueOf(lblSellingPrice.getText()),
                Integer.valueOf(lblQty.getText())
        );
        System.out.println("items : "+item);
        if ( service.additem(item)){
            new Alert(Alert.AlertType.CONFIRMATION,"Done").show();
            reloard();
            clear();
            id();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Item item = new Item(
                lblItemCode.getText(),
                cmdSupplierId.getValue(),
                lblItemName.getText(),
                lblSupplierName.getText(),
                lblSize.getText(),
                lblcategorie.getValue(),
                Double.valueOf(lblBynigPrice.getText()),
                Double.valueOf(lblSellingPrice.getText()),
                Integer.valueOf(lblQty.getText())
        );
        System.out.println("items : "+item);
        if ( service.update(item)){
            new Alert(Alert.AlertType.CONFIRMATION,"Done").show();
            reloard();
        }
    }

    private void reloard(){

        ObservableList<Item> allItem = service.getAllItem();
        tblItem.setItems(allItem);
        System.out.println("get All "+allItem);

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {

        if (service.delete(lblItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"delte item").show();
            reloard();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            parent = FXMLLoader.load(getClass().getResource("../../view/dashboard.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(parent);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clear(){
        lblItemCode.clear();
        lblItemName.clear();
        lblQty.clear();
        lblSupplierName.clear();
        lblBynigPrice.clear();
        lblSellingPrice.clear();
        lblSize.clear();
        lblcategorie.setValue(null);
    }
//******
    public void setSupplierId(){
        List<String> suplierId = SupplierController.getInstance().getallSuplierId();
        ObservableList<String> observableList = FXCollections.observableArrayList();

        for (String id : suplierId) {
            observableList.add(id);
        }
        cmdSupplierId.setItems(observableList);
    }
//======================================================================================================
    private String getlatesitemid() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT itemCode FROM item ORDER BY itemCode DESC LIMIT 1");
        String itemCode = resultSet.next() ? resultSet.getString("ItemCode") : null;
        System.out.println("Fetched Item Code from DB: " + itemCode); // Print the fetched item code
        return itemCode;
    }

    private void id(){
        try {
            String getlatesitemid = getlatesitemid();  // Get the latest item ID from the DB

            if (getlatesitemid != null && getlatesitemid.matches("i-\\d{4}")) {
                // Extract the numeric part and increment it
                int numericPart = Integer.parseInt(getlatesitemid.substring(2)) + 1;  // Get the number after 'P-'
                String newItemId = String.format("i-%04d", numericPart);  // Format with leading zeros
                lblItemCode.setText(newItemId);
            } else {
                // If no valid itemCode exists, start from P-0001
                new Alert(Alert.AlertType.ERROR,"pleace enter valid id").show();
                lblItemCode.setText("i-0001");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Handle SQL exceptions
        }
    }





//    @FXML
//    void btnBackOnAction(ActionEvent event) {
//        try {
//            parent = FXMLLoader.load(getClass().getResource("../view/dashboard.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(parent);
//
//        stage.setScene(scene);
//        stage.show();
//    }

}
