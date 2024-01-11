package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.OrderDto;
import lk.ijse.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order> {
    List<Order> getAllOdersByDate(String date) throws SQLException, ClassNotFoundException;
}
