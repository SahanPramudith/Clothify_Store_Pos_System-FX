package controller.employee;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeerFormController {
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

    @FXML
    private ComboBox<?> txtTiel;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeletOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event){

    }
}
