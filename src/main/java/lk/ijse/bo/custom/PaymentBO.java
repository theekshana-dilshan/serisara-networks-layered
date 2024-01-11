package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    List<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException;
    boolean setPayment(PaymentDto dto) throws SQLException, ClassNotFoundException;
    boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException;
    boolean deletePayment(String pId) throws SQLException, ClassNotFoundException;
    String generateNextPaymentId() throws SQLException, ClassNotFoundException;
}
