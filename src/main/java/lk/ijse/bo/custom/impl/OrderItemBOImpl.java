package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderItemBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.OrderItemDAO;
import lk.ijse.dto.tm.OrderTm;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class OrderItemBOImpl implements OrderItemBO {

    OrderItemDAO orderItemDAO= (OrderItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_ITEM);
    @Override
    public boolean saveOrderDetails(String orderId, OrderTm tm) throws SQLException, ClassNotFoundException {
        return orderItemDAO.saveOrderDetails(orderId, tm);
    }

    @Override
    public boolean saveOrderDetails(String orderId, List<OrderTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(OrderTm tm : cartTmList) {
            if(!saveOrderDetails(orderId, tm)) {
                return false;
            }
        }
        return true;
    }
}
