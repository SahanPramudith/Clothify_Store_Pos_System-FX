package controller.employee;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Employer;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class EmployerFormController implements Initializable {

    public ComboBox<String> cmdTitel;
    @FXML
    private ComboBox<?> CodeLite;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<?> tblEmp;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmpId;

    @FXML
    private JFXTextField txtMail;

    @FXML
    private JFXTextField txtName;

    EmployerService service=new EmployerController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id();
        ObservableList<String> titel = FXCollections.observableArrayList();
        titel.add("Mr.");
        titel.add("Ms.");
        titel.add("Mss.");
        cmdTitel.setItems(titel);
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        Employer employer = new Employer(
                txtEmpId.getText(),
                cmdTitel.getValue(),
                txtAddress.getText(),
                txtCompany.getText(),
                txtCompany.getText(),
                txtMail.getText()

        );
        if (service.add(employer)){
            new Alert(Alert.AlertType.CONFIRMATION).show();
        }

    }

    @FXML
    void btnDeletOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }


    //-----------------------id------------------------------------------------------------------

    private String getlatesitemid() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EpmploerId FROM Emploer ORDER BY EpmploerId DESC LIMIT 1");
        String itemCode = resultSet.next() ? resultSet.getString("EpmploerId") : null;
        System.out.println("Fetched Item Code from DB: " + itemCode); // Print the fetched item code
        return itemCode;
    }

    private void id(){
        try {
            String getlatesitemid = getlatesitemid();  // Get the latest item ID from the DB

            if (getlatesitemid != null && getlatesitemid.matches("E-\\d{4}")) {
                // Extract the numeric part and increment it
                int numericPart = Integer.parseInt(getlatesitemid.substring(2)) + 1;  // Get the number after 'P-'
                String newItemId = String.format("E-%04d", numericPart);  // Format with leading zeros
                txtEmpId.setText(newItemId);
            } else {
                // If no valid itemCode exists, start from P-0001
                new Alert(Alert.AlertType.ERROR,"pleace enter valid id").show();
                txtEmpId.setText("E-0001");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);  // Handle SQL exceptions
        }
    }



}
