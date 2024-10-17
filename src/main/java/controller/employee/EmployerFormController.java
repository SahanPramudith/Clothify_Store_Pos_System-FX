package controller.employee;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Employer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventObject;
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
    private TableView<Employer> tblEmp;

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

    private Parent parent;
    private Stage stage;
    private EventObject actionEvent;
    private Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reloard();
        id();

        ObservableList<String> titel = FXCollections.observableArrayList();
        titel.add("Mr.");
        titel.add("Ms.");
        titel.add("Mss.");
        cmdTitel.setItems(titel);

        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empid"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("titel"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("mail"));

        tblEmp.getSelectionModel().selectedItemProperty().addListener((observableValue, employer, t1) -> {
            if (t1!=null){
                setToTable(t1);
            }
        });
    }

    private void setToTable(Employer t1) {
        txtName.setText(t1.getName());
        txtCompany.setText(t1.getCompany());
        txtAddress.setText(t1.getAddress());
        txtMail.setText(t1.getMail());
        txtEmpId.setText(t1.getEmpid());
        cmdTitel.setValue(t1.getTitel());
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        Employer employer = new Employer(
                txtEmpId.getText(),
                cmdTitel.getValue(),
                txtName.getText(),
                txtAddress.getText(),
                txtCompany.getText(),
                txtMail.getText()

        );
        if (service.add(employer)){
            new Alert(Alert.AlertType.CONFIRMATION).show();
            reloard();
            clear();
        }

    }

    @FXML
    void btnDeletOnAction(ActionEvent event) {
        if (service.delete(txtEmpId.getText())){
            new Alert(Alert.AlertType.WARNING).show();
            reloard();
            clear();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Employer employer = new Employer(
                txtEmpId.getText(),
                cmdTitel.getValue(),
                txtAddress.getText(),
                txtCompany.getText(),
                txtCompany.getText(),
                txtMail.getText()
        );

        if (service.update(employer)){
            new Alert(Alert.AlertType.CONFIRMATION).show();
            reloard();
            //clear();
        }


    }

    void reloard(){
        ObservableList<Employer> getall = service.getall();
        System.out.println("getall = " + getall);
        tblEmp.setItems(getall);
        clear();
    }

    private void clear(){
        txtEmpId.clear();
        txtMail.clear();
        txtAddress.clear();
        txtName.clear();
        txtCompany.clear();
        cmdTitel.setValue("");

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
}
