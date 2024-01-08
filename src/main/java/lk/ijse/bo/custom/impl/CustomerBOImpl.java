package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto>customerDTOS=new ArrayList<>();
        ArrayList<Customer>customers=customerDAO.getAll();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDto(customer.getCId(),customer.getName(),customer.getEmail(),customer.getAddress(),customer.getContact(),customer.getUserId()));
        }
        return customerDTOS;
    }
    @Override
    public boolean setCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCId(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getContact(),dto.getUserId()));
    }
    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCId(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getContact(),dto.getUserId()));
    }
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    public CustomerDto getCustomer(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE cId =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id );
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCId(resultSet.getString(1));
            customerDto.setName(resultSet.getString(2));
            customerDto.setEmail(resultSet.getString(3));
            customerDto.setAddress(resultSet.getString(4));
            customerDto.setContact(resultSet.getString(5));
            customerDto.setUserId(resultSet.getString(6));
            return customerDto;
        }
        return null;
    }

    public CustomerDto getCustomerByName(String name) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE name =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name );
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCId(resultSet.getString(1));
            customerDto.setName(resultSet.getString(2));
            customerDto.setEmail(resultSet.getString(3));
            customerDto.setAddress(resultSet.getString(4));
            customerDto.setContact(resultSet.getString(5));
            customerDto.setUserId(resultSet.getString(6));
            return customerDto;
        }
        return null;
    }
    @Override
    public String generateNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }
}
