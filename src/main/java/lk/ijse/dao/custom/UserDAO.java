package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    User getUserByName(String userName) throws SQLException, ClassNotFoundException;
    User getUserDtoList(String userId) throws SQLException, ClassNotFoundException;
    boolean update(String password, String userId) throws SQLException, ClassNotFoundException;
}
