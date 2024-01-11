package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    Customer getCustomer(String id) throws SQLException, ClassNotFoundException;

    Customer getCustomerByName(String name) throws SQLException, ClassNotFoundException;
}
