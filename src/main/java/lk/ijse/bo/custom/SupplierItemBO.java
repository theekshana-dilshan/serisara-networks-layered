package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface SupplierItemBO extends SuperBO {
    boolean saveSupplierItem(String itemId, String supId) throws SQLException, ClassNotFoundException;
}
