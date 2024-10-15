package controller.itemController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;

import java.net.URL;
import java.util.EventObject;
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
        reloard();
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
    }

    private void addValueTotext(Item newval) {
        lblItemCode.setText(newval.getItemcode());
        lblSize.setText(newval.getSize());
        lblQty.setText(String.valueOf(newval.getQty()));
        lblSellingPrice.setText(""+newval.getSellingPrice());
        lblBynigPrice.setText(""+newval.getBynigPrice());
        lblItemName.setText(newval.getItemname());

    }

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

    public void btnBackOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
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
