package controller.supplierController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SuppliersController {

    @FXML
    private ComboBox<?> cmdTitel;

    @FXML
    private ComboBox<?> cmdTitel1;

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

    @FXML
    void btnAddOnaction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
