package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {
    UserDto getUserByName(String userName) throws SQLException, ClassNotFoundException;
    UserDto getUserDtoList(String userId) throws SQLException, ClassNotFoundException;
    boolean setUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean updateUser (String password, String userId) throws SQLException, ClassNotFoundException;
    String generateNextUserId() throws SQLException, ClassNotFoundException;
}
