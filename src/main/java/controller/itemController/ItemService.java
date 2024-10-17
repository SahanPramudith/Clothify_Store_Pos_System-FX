package controller.itemController;

import javafx.collections.ObservableList;
import model.Item;

import java.util.List;

public interface ItemService {
    boolean additem(Item item);

    boolean update(Item item);

    ObservableList<Item> getAllItem();

    boolean delete(String text);
//    Item search(String id);
//    List<String> getsuppid();
//    ObservableList<Item>setsupid(String id );

}
