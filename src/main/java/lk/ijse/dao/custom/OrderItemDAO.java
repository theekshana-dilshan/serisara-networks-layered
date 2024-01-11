package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.tm.OrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderItemDAO extends SuperDAO {
    boolean saveOrderDetails(String orderId, OrderTm tm) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetails(String orderId, List<OrderTm> cartTmList) throws SQLException, ClassNotFoundException;
}
