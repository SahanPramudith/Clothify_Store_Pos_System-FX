package controller.supplierController;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Supplier;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SuppliersFormController implements Initializable {

    public TextField txtId;
    @FXML
    private ComboBox<String> cmdTitel;

    @FXML
    private ComboBox<Supplier> cmdTitel1;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colMail;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label lblSupplierId;

    @FXML
    private TableView<?> tblSupplier;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtmail;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id();

        ObservableList<String> title = FXCollections.observableArrayList();
        title.add("Mr.");
        title.add("Ms.");
        title.add("Mss.");

        cmdTitel.setItems(title);


    }



    SupplierService service=new SupplierController();

    @FXML
    void btnAddOnaction(ActionEvent event) {
        Supplier supplier = new Supplier(
           txtId.getText(),
            txtName.getText(),
            cmdTitel.getValue(),
            txtContact.getText(),
            txtCompany.getText(),
            txtmail.getText(),
            txtItem.getText()
        );

        if (service.addSupplier(supplier)){
            new Alert(Alert.AlertType.CONFIRMATION,"Done").show();
            id();
        }



    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    //-----------------------id------------------------------------------------------------------

    private String getlatesitemid() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT SupplierId FROM supplier ORDER BY SupplierId DESC LIMIT 1");
        String itemCode = resultSet.next() ? resultSet.getString("SupplierId") : null;
        System.out.println("Fetched Item Code from DB: " + itemCode); // Print the fetched item code
        return itemCode;
    }

    private void id(){
        try {
            String getlatesitemid = getlatesitemid();  // Get the latest item ID from the DB

            if (getlatesitemid != null && getlatesitemid.matches("s-\\d{4}")) {
                // Extract the numeric part and increment it
                int numericPart = Integer.parseInt(getlatesitemid.substring(2)) + 1;  // Get the number after 'P-'
                String newItemId = String.format("s-%04d", numericPart);  // Format with leading zeros
                txtId.setText(newItemId);
            } else {
                // If no valid itemCode exists, start from P-0001
                new Alert(Alert.AlertType.ERROR,"pleace enter valid id").show();
                txtId.setText("s-0001");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Handle SQL exceptions
        }
    }



}
