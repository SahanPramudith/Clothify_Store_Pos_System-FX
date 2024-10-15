package controller.itemController;

import db.DbConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemController implements ItemService{
    @Override
    public boolean additem(Item item) {

        boolean isAdd;
        try {
            String sql="Insert Into item values (?,?,?,?,?,?,?,?,?)";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setObject(1,item.getItemcode());
            psTm.setObject(2,item.getSpplierid());
            psTm.setObject(3,item.getItemname());
            psTm.setObject(4,item.getSupplier());
            psTm.setObject(5,item.getSize());
            psTm.setObject(6,item.getCategorie());
            psTm.setObject(7,item.getBynigPrice());
            psTm.setObject(8,item.getSellingPrice());
            psTm.setObject(9,item.getQty());

             isAdd = psTm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (isAdd){
            return true;
        }
        return false;
    }
}
