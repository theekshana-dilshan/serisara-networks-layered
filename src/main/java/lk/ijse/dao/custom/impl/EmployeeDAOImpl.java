package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> getAllEmployee=new ArrayList<>();
        while (rst.next()){
            Employee entity=new Employee(rst.getString("empId"), rst.getString("name"), rst.getString("address"), rst.getString("position"), rst.getString("contact"), rst.getString("salary"), rst.getString("userId"));
            getAllEmployee.add(entity);
        }
        return getAllEmployee;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee (empId,name, address, position, contact, salary, userId) VALUES (?,?,?,?,?,?,?)",
                entity.getEmpId(), entity.getName(), entity.getAddress(), entity.getPosition(), entity.getContact(), entity.getSalary(), entity.getUserId());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name=?, address=?, position=?, contact=?, salary=? WHERE empId=?"
                , entity.getName(), entity.getAddress(), entity.getPosition(), entity.getContact(), entity.getSalary(), entity.getEmpId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE empId=?",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT empId FROM employee ORDER BY empId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("empId");
            int newEmployeeId = Integer.parseInt(id.replace("E00-", "")) + 1;
            return String.format("E00-%03d", newEmployeeId);
        } else {
            return "E00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT empId FROM employee WHERE empId=?",id);
        return resultSet.next();
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE empId=?",id);
        rst.next();
        return new Employee(id + "", rst.getString("name"), rst.getString("address"), rst.getString("position"), rst.getString("contact"), rst.getString("salary"), rst.getString("userId"));
    }

    @Override
    public Employee getEmployee(String empId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE empId=?",empId);
        rst.next();
        return new Employee(empId + "", rst.getString("name"), rst.getString("address"), rst.getString("position"), rst.getString("contact"), rst.getString("salary"), rst.getString("userId"));
    }
}
