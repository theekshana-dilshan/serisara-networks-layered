package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            Customer entity=new Customer(rst.getString("id"), rst.getString("name"), rst.getString("email"), rst.getString("address"), rst.getString("contact"), rst.getString("userId"));
            getAllCustomer.add(entity);
        }
        return getAllCustomer;
    }
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer (id,name, email, address, contact, userId) VALUES (?,?,?,?,?,?)",
                entity.getCId(), entity.getName(), entity.getEmail(), entity.getAddress(), entity.getContact(), entity.getUserId());
    }
    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, email=?, address=?, contact=? WHERE id=?"
                ,entity.getName(), entity.getEmail(), entity.getAddress(), entity.getContact(),entity.getCId());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE cId=?",id);
    }
    @Override
    public  String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT cId FROM Customer ORDER BY cId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("cId");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT cId FROM Customer WHERE cId=?",id);
        return resultSet.next();
    }
    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE cId=?",id);
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("email"), rst.getString("address"), rst.getString("contact"), rst.getString("userId"));
    }
}
