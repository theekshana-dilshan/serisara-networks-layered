package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.OrderTm;
import lk.ijse.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateItemBySupply(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException;
    boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException;
    Item getItem(String itemName) throws SQLException, ClassNotFoundException;
    Item getItemById (String id) throws SQLException, ClassNotFoundException;
}
