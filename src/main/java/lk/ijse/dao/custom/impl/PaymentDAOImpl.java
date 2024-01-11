package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment");
        ArrayList<Payment> getAllPayment=new ArrayList<>();
        while (rst.next()){
            Payment entity=new Payment(rst.getString("pId"), rst.getDouble("amount"), rst.getString("status"), rst.getDate("date").toLocalDate(), rst.getString("orderId"));
            getAllPayment.add(entity);
        }
        return getAllPayment;
    }

    @Override
    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment (pId,amount, status, date, orderId) VALUES (?,?,?,?,?)",
                entity.getPId(), entity.getAmount(), entity.getStatus(), entity.getDate(), entity.getOId());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET amount=?, status=?, date=?, orderId=? WHERE pId=?"
                ,entity.getAmount(), entity.getStatus(), entity.getDate(), entity.getOId(),entity.getPId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE pId=?",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT pId FROM payment ORDER BY pId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("pId");
            int newPaymentId = Integer.parseInt(id.replace("P00-", "")) + 1;
            return String.format("P00-%03d", newPaymentId);
        } else {
            return "P00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT pId FROM payment WHERE pId=?",id);
        return resultSet.next();
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment WHERE pId=?",id);
        rst.next();
        return new Payment(id + "", rst.getDouble("amount"), rst.getString("status"), rst.getDate("date").toLocalDate(), rst.getString("orderId"));
    }
}
