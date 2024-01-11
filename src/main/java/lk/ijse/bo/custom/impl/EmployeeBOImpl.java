package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public List<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeDtos=new ArrayList<>();
        ArrayList<Employee>employees=employeeDAO.getAll();
        for (Employee employee:employees) {
            employeeDtos.add(new EmployeeDto(employee.getEmpId(),employee.getName(),employee.getAddress(),employee.getPosition(),employee.getContact(),employee.getSalary(),employee.getUserId()));
        }
        return employeeDtos;
    }

    @Override
    public boolean saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDto.getEmpId(),employeeDto.getName(),employeeDto.getAddress(),employeeDto.getPosition(),employeeDto.getContact(),employeeDto.getSalary(), employeeDto.getUserId()));
    }

    @Override
    public boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDto.getEmpId(),employeeDto.getName(),employeeDto.getAddress(),employeeDto.getPosition(),employeeDto.getContact(),employeeDto.getSalary(), employeeDto.getUserId()));
    }

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(empId);
    }

    @Override
    public EmployeeDto getEmployee(String empId) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.getEmployee(empId);
        EmployeeDto dto = new EmployeeDto(employee.getEmpId(), employee.getName(), employee.getAddress(), employee.getPosition(), employee.getContact(), employee.getSalary(), employee.getUserId());
        return dto;
    }

    @Override
    public String generateNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewId();
    }
}
