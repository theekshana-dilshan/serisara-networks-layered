package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.OrderDto;
import lk.ijse.dto.tm.OrderTm;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public List<OrderDto> getAllOders() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDto> orderDtos=new ArrayList<>();
        ArrayList<Order>orders=orderDAO.getAll();
        List<OrderTm> orderTmList = new ArrayList<>();
        for (Order order:orders) {
            orderDtos.add(new OrderDto(order.getOrderId(),order.getDate(),order.getCId(),orderTmList));
        }
        return orderDtos;
    }

    @Override
    public List<OrderDto> getAllOdersByDate(String date) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDto> orderDtos=new ArrayList<>();
        ArrayList<Order>orders= (ArrayList<Order>) orderDAO.getAllOdersByDate(date);
        List<OrderTm> orderTmList = new ArrayList<>();
        for (Order order:orders) {
            orderDtos.add(new OrderDto(order.getOrderId(),order.getDate(),order.getCId(),orderTmList));
        }
        return orderDtos;
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate date, String customerId) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(orderId,date,customerId));
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }
}
