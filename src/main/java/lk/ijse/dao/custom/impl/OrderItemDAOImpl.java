package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderItemDAO;
import lk.ijse.dto.tm.OrderTm;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    @Override
    public boolean saveOrderDetails(String orderId, OrderTm tm) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orderItemDetail VALUES(?, ?, ?, ?)",
                tm.getQty(), tm.getUnitPrice(), tm.getItemId(), orderId);
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
