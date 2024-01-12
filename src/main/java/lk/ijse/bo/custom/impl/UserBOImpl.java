package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public UserDto getUserByName(String userName) throws SQLException, ClassNotFoundException {
        User user = userDAO.getUserByName(userName);
        UserDto dto = new UserDto(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail());
        return dto;
    }

    @Override
    public UserDto getUserDtoList(String userId) throws SQLException, ClassNotFoundException {
        User user = userDAO.getUserDtoList(userId);
        UserDto dto = new UserDto(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail());
        return dto;
    }

    @Override
    public boolean setUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUserId(),dto.getUserName(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public boolean updateUser(String password, String userId) throws SQLException, ClassNotFoundException {
        return userDAO.update(password, userId);
    }

    @Override
    public String generateNextUserId() throws SQLException, ClassNotFoundException {
        return userDAO.generateNewId();
    }
}
