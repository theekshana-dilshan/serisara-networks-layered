package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException;
    boolean setSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException;
    SupplierDto getSupplier(String supId) throws SQLException, ClassNotFoundException;
    SupplierDto getSupplierByName(String name) throws SQLException, ClassNotFoundException;
    String generateNextSupplierId() throws SQLException, ClassNotFoundException;
}
