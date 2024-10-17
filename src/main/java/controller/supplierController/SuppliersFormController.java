package controller.supplierController;

import controller.itemController.ItemController;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.Supplier;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class SuppliersFormController implements Initializable {

    public TextField txtId;
    public ComboBox <String>cmdSuppid;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableView tblItem;
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
    private TableView  tblSupplier;

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
        reloard();
        getsupid();

        colSupId.setCellValueFactory(new PropertyValueFactory<>("supid"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("titel"));


//        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
//        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));



        ObservableList<String> title = FXCollections.observableArrayList();
        title.add("Mr.");
        title.add("Ms.");
        title.add("Mss.");

        cmdTitel.setItems(title);

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newval) -> {
            if(newval!=null){
                addToText((Supplier) newval);
            }
        });

        cmdSuppid.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if (t1!=null){
                setItemId(t1);
                getsupid(t1);
            }
        });

    }

    private void setItemId(String id) {
//        System.out.println("id1 = " + id); //done
//        Item search = ItemController.getInstance().search(id);
//        System.out.println("searc supid = " + search);
////        System.out.println("search = " + search);
//        tblItem.setItems(search);
    }

    private void getsupid(String id){
//        ObservableList<Item> setsupid = ItemController.getInstance().setsupid(id);
//        System.out.println("setsupid = " + setsupid);

    }

    private void addToText(Supplier newval) {
        txtId.setText(newval.getSupid());
        txtContact.setText(newval.getContact());
        txtmail.setText(newval.getMail());
        cmdTitel.setValue(newval.getTitel());
        txtName.setText(newval.getSupname());
        txtCompany.setText(newval.getCompany());
    }


    SupplierService service =SupplierController.getInstance();

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
            reloard();
            clear();
            id();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (service.delete(txtId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"deleted").show();
            reloard();
            clear();
            id();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtId.getText(),
                txtName.getText(),
                cmdTitel.getValue(),
                txtContact.getText(),
                txtCompany.getText(),
                txtmail.getText(),
                txtItem.getText()
        );

        if (service.update(supplier)){
            new Alert(Alert.AlertType.CONFIRMATION,"Update").show();
            reloard();
            clear();
            id();

        }
    }

    private void reloard(){

        ObservableList<Supplier> allSupplier = service.getAllSupplier();
        tblSupplier.setItems(allSupplier);
    }

    private void clear(){
        txtId.clear();
        txtCompany.clear();
        txtName.clear();
        txtmail.clear();
        txtItem.clear();
        txtContact.clear();
        cmdTitel.setValue(null);
    }

    public void getsupid(){
//        List<String> getsuppid = ItemController.getInstance().getsuppid();
//        ObservableList<String> observableList = FXCollections.observableArrayList();
//
//        getsuppid.forEach(id -> {
//            observableList.add(id);
//            System.out.println("id = " + id);
//        });
//        System.out.println("observableList = " + observableList);
//        cmdSuppid.setItems(observableList);
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
