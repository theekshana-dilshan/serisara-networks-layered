package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.OrderDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface OrderBO extends SuperBO {
    List<OrderDto> getAllOders() throws SQLException, ClassNotFoundException;
    List<OrderDto> getAllOdersByDate(String date) throws SQLException, ClassNotFoundException;
    boolean saveOrder(String orderId,LocalDate date, String customerId ) throws SQLException, ClassNotFoundException;
    String generateNextOrderId() throws SQLException, ClassNotFoundException;
}
