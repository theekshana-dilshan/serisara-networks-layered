package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.OrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
    List<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;
    boolean setItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItemDto(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItemBySupply(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException;
    boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException;
    ItemDto getItem(String itemName) throws SQLException, ClassNotFoundException;
    ItemDto getItemById (String id) throws SQLException, ClassNotFoundException;
    String generateNextItemId() throws SQLException, ClassNotFoundException;
}
