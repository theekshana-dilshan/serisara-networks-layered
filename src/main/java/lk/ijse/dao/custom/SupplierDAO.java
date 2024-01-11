package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.SupplierDto;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier> {
    Supplier getSupplier(String supId) throws SQLException, ClassNotFoundException;
    Supplier getSupplierByName(String name) throws SQLException, ClassNotFoundException;
}
