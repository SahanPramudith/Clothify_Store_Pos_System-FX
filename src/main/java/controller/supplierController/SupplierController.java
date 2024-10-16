package controller.supplierController;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        ObservableList<Supplier> getallitem = FXCollections.observableArrayList();
        try {
            String sql="select * from supplier";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getString("SupplierId"),
                        resultSet.getString("SupplierName"),
                        resultSet.getString("SupplierTitel"),
                        resultSet.getString("contact"),
                        resultSet.getString("company"),
                        resultSet.getString("mail"),
                        resultSet.getString("ItemName")

                );
                getallitem.add(supplier);
                System.out.println("item = " + supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getallitem;

    }

    @Override
    public boolean delete(String text) {
        return false;
    }
}
