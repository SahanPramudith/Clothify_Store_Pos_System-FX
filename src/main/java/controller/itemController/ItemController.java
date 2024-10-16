package controller.itemController;

import controller.util.CrudUtil;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService{


    //======================================================================================================
    private static ItemController instance;

    private ItemController(){}

    public static ItemController getInstance() {
        return instance==null?instance=new ItemController():instance;
    }
//======================================================================================================
    @Override
    public boolean additem(Item item) {

        boolean isAdd;
        try {
            String sql="Insert Into items values (?,?,?,?,?,?,?,?,?)";
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
        return isAdd;
    }

    @Override
    public boolean update(Item item) {

        boolean isupdate;
        try {
            String sql="Update items set SupplierId=?,ItemName=?,Supplier=?,Size=?,categorie=?,BynigPrice=?,SellingPrice=?,Qty=? where ItemCode=? ";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            psTm.setObject(1,item.getSpplierid());
            psTm.setObject(2,item.getItemname());
            psTm.setObject(3,item.getSupplier());
            psTm.setObject(4,item.getSize());
            psTm.setObject(5,item.getCategorie());
            psTm.setObject(6,item.getBynigPrice());
            psTm.setObject(7,item.getSellingPrice());
            psTm.setObject(8,item.getQty());
            psTm.setObject(9,item.getItemcode());

             isupdate = psTm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isupdate;
    }

    @Override
    public ObservableList<Item> getAllItem() {

        ObservableList<Item> getallitem = FXCollections.observableArrayList();

        try {
            String sql="select * from items";
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(sql);
            ResultSet resultSet = psTm.executeQuery();

            while (resultSet.next()){
                Item item = new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("SupplierId"),
                        resultSet.getString("ItemName"),
                        resultSet.getString("Supplier"),
                        resultSet.getString("Size"),
                        resultSet.getString("categorie"),
                        resultSet.getDouble("BynigPrice"),
                        resultSet.getDouble("SellingPrice"),
                        resultSet.getInt("Qty")

                );
                    getallitem.add(item);
                System.out.println("item = " + item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getallitem;
    }

    @Override
    public boolean delete(String id) {

        boolean isdelete;
        try {
            String sql="delete form item where ItemCode=?"+id;
            isdelete = DbConnection.getInstance().getConnection().createStatement().executeUpdate("delete from items where ItemCode='" + id + "'") > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isdelete;
    }
//
//    @Override
//    public Item search(String id) {
//        try {
//            ResultSet resultSet = CrudUtil.execute("select * from items where ItemCode=?", id);
//            while (resultSet.next()){
//                if (!resultSet.next()) {
//                    System.out.println("No data found for ID: " + id);
//                }
//                return new Item(
//                        resultSet.getString("ItemCode"),
//                        resultSet.getString("SupplierId"),
//                        resultSet.getString("ItemName"),
//                        resultSet.getString("Supplier"),
//                        resultSet.getString("Size"),
//                        resultSet.getString("categorie"),
//                        resultSet.getDouble("BynigPrice"),
//                        resultSet.getDouble("SellingPrice"),
//                        resultSet.getInt("Qty")
//                );
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//
//
//    @Override
//    public List<String> getsuppid() {
//        ObservableList<Item> allItem = getAllItem();
//        System.out.println("allItemform = " + allItem);
//
//        ArrayList<String> itemcode = new ArrayList<>();
//
//        allItem.forEach(code->{
//            itemcode.add(code.getSpplierid());
//        });
//        System.out.println("itemformcode = " + itemcode);
//        return itemcode;
//    }
//
//
//    public ObservableList<Item>setsupid(String id ){
//        ObservableList<Item> getallitem = FXCollections.observableArrayList();
//
//        try {
//            String sql="select * from items";
//            Connection connection = DbConnection.getInstance().getConnection();
//            PreparedStatement psTm = connection.prepareStatement("select * from items where ItemCode='" + id + "'");
//            ResultSet resultSet = psTm.executeQuery();
//
//            while (resultSet.next()){
//                Item item = new Item(
//                        resultSet.getString("ItemCode"),
//                        resultSet.getString("SupplierId"),
//                        resultSet.getString("ItemName"),
//                        resultSet.getString("Supplier"),
//                        resultSet.getString("Size"),
//                        resultSet.getString("categorie"),
//                        resultSet.getDouble("BynigPrice"),
//                        resultSet.getDouble("SellingPrice"),
//                        resultSet.getInt("Qty")
//
//                );
//                getallitem.add(item);
//                System.out.println("item = " + item);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("getallitems = " + getallitem);
//        return getallitem;
//    }

}
