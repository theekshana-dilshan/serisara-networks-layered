package lk.ijse.dao.custom.impl;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user (userId,userName, password, email) VALUES (?,?,?,?)",
                entity.getUserId(), entity.getUserName(), entity.getPassword(), entity.getEmail());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET userName=?, password=?, email=?WHERE userId=?"
                ,entity.getUserName(), entity.getPassword(), entity.getEmail(), entity.getUserId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT userId FROM user ORDER BY userId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("userId");
            int newUserId = Integer.parseInt(id.replace("U00-", "")) + 1;
            return String.format("U00-%03d", newUserId);
        } else {
            return "U00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public User getUserByName(String userName) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE cId=?",userName);
        rst.next();
        return new User(rst.getString("userId"), userName + "", rst.getString("password"), rst.getString("email"));
    }

    @Override
    public User getUserDtoList(String userId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE cId=?",userId);
        rst.next();
        return new User(userId + "", rst.getString("userName"), rst.getString("password"), rst.getString("email"));
    }

    @Override
    public boolean update(String password, String userId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET password=? WHERE userId=?"
                , password, userId);
    }
}
