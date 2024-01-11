package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dto.OrderDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders");
        ArrayList<Order> getAllOrders=new ArrayList<>();
        while (rst.next()){
            Order entity=new Order(rst.getString("id"), rst.getDate("date").toLocalDate(), rst.getString("email"));
            getAllOrders.add(entity);
        }
        return getAllOrders;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders (orderId,date, cId) VALUES (?,?,?)",
                entity.getOrderId(), entity.getDate(), entity.getCId());
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("orderId");
            int newOrderId = Integer.parseInt(id.replace("O00-", "")) + 1;
            return String.format("O00-%03d", newOrderId);
        } else {
            return "O00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Order> getAllOdersByDate(String date) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM orders WHERE date = ?");
        ArrayList<Order> getAllOrders=new ArrayList<>();
        while (rst.next()){
            Order entity=new Order(rst.getString("id"), rst.getDate("date").toLocalDate(), rst.getString("email"));
            getAllOrders.add(entity);
        }
        return getAllOrders;
    }
}
