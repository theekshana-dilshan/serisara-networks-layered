package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierItemDAO;

import java.sql.SQLException;

public class SupplierItemDAOImpl implements SupplierItemDAO {

    @Override
    public boolean saveSupplierItem(String supplierId, String itemId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO itemSupplierDetail VALUES(?, ?)", itemId, supplierId);
    }
}
