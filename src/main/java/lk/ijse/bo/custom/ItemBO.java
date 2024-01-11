package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    boolean existItem(String id) throws SQLException, ClassNotFoundException;
}
