package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierItemBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.SupplierDAO;

import java.sql.SQLException;

public class SupplierItemBOImpl implements SupplierItemBO {
    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public boolean saveSupplierItem(String itemId, String supId) throws SQLException, ClassNotFoundException {
        return supplierDAO.saveSupplierItem(itemId, supId);
    }
}
