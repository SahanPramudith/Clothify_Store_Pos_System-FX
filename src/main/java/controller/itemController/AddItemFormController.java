package controller.itemController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Item;

import java.io.IOException;
import java.util.EventObject;

public class AddItemFormController {

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
    private TableView<?> tblItem;

    ItemService service=new ItemController();

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
        }
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public void btnBackOnAction(ActionEvent actionEvent) {

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
