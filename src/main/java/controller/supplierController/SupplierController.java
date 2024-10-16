package controller.supplierController;

import db.DbConnection;
import javafx.collections.ObservableList;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierController implements SupplierService{


    @Override
    public boolean addSupplier(Supplier supplier) {

        boolean isAdd;
        try {
            String sql="Insert Into supplier values (?,?,?,?,?,?,?)";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setObject(1,supplier.getSupid());
            psTm.setObject(2,supplier.getSupname());
            psTm.setObject(3,supplier.getTitel());
            psTm.setObject(4,supplier.getContact());
            psTm.setObject(5,supplier.getCompany());
            psTm.setObject(6,supplier.getMail());
            psTm.setObject(7,supplier.getItemname());

            isAdd = psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdd;
    }

    @Override
    public boolean update(Supplier supplier) {
        return false;
    }

    @Override
    public ObservableList<Supplier> getAllSupplier() {
        return null;
    }

    @Override
    public boolean delete(String text) {
        return false;
    }
}
