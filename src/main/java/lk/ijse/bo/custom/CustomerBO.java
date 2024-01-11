package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    boolean setCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer (String id) throws SQLException, ClassNotFoundException;

    CustomerDto getCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDto getCustomerByName(String name) throws SQLException, ClassNotFoundException;

    String generateNextCustomerId() throws SQLException, ClassNotFoundException;
}
