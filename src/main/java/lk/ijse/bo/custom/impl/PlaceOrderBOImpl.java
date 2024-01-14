package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.OrderBO;
import lk.ijse.bo.custom.OrderItemBO;
import lk.ijse.bo.custom.PlaceOrderBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.OrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderBO orderBO= (OrderBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER);
    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ITEM);
    OrderItemBO orderItemBO = (OrderItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ORDER_ITEM);

    public  boolean placeOrder(OrderDto orderDto) throws SQLException {

        String orderId = orderDto.getOrderId();
        String customerId = orderDto.getCId();
        LocalDate date = orderDto.getDate();

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderBO.saveOrder(orderId, date, customerId);
            if (isOrderSaved) {
                boolean isUpdated = itemBO.updateItem(orderDto.getOrderTmList());
                if(isUpdated) {
                    boolean isOrderDetailSaved = orderItemBO.saveOrderDetails(orderDto.getOrderId(), orderDto.getOrderTmList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }

        return false;
    }
}
