package controller.itemController;

import javafx.collections.ObservableList;
import model.Item;

public interface ItemService {
    boolean additem(Item item);

    boolean update(Item item);

    ObservableList<Item> getAllItem();

    boolean delete(String text);
}
