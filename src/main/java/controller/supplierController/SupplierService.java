package controller.supplierController;

import javafx.collections.ObservableList;
import model.Item;
import model.Supplier;

import java.sql.SQLException;

public interface SupplierService {
    boolean addSupplier(Supplier supplier);

    boolean update(Supplier supplier);

    ObservableList<Supplier > getAllSupplier();

    boolean delete(String text);
}
