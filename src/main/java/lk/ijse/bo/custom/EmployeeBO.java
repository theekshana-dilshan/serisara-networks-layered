package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    List<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;
    boolean saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException;
    EmployeeDto getEmployee(String empId) throws SQLException, ClassNotFoundException;
    String generateNextEmployeeId() throws SQLException, ClassNotFoundException;
}
