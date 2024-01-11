package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.QueryDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Object dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Object dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Object search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
