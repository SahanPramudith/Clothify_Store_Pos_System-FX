package controller.supplierController;

import javafx.collections.ObservableList;
import model.Item;
import model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService {
    boolean addSupplier(Supplier supplier);

    boolean update(Supplier supplier);

    ObservableList<Supplier > getAllSupplier();

    boolean delete(String text);
    Supplier SearchSupplier(String id);
    List<String> getallSuplierId();
}
