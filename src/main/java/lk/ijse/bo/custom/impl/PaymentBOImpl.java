package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public List<PaymentDto> getAllPayments() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDto> paymentDtos=new ArrayList<>();
        ArrayList<Payment>payments=paymentDAO.getAll();
        for (Payment payment:payments) {
            paymentDtos.add(new PaymentDto(payment.getPId(),payment.getAmount(),payment.getStatus(),payment.getDate(),payment.getOId()));
        }
        return paymentDtos;
    }

    @Override
    public boolean setPayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPId(),dto.getAmount(),dto.getStatus(),dto.getDate(),dto.getOId()));
    }

    @Override
    public boolean updatePayment(PaymentDto dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPId(),dto.getAmount(),dto.getStatus(),dto.getDate(),dto.getOId()));
    }

    @Override
    public boolean deletePayment(String pId) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(pId);
    }

    @Override
    public String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewId();
    }
}
