package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SupplierItemDAO extends SuperDAO {
    boolean saveSupplierItem(String supplierId , String itemId) throws SQLException, ClassNotFoundException;
}
