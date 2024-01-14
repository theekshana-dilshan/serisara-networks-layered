package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.tm.OrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderItemBO extends SuperBO {
    boolean saveOrderDetails(String orderId, OrderTm tm) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetails(String orderId, List<OrderTm> cartTmList) throws SQLException, ClassNotFoundException;
}
