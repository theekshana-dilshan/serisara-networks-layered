package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;

public interface ReStockBO extends SuperBO {
    boolean itemSupplier(SupplierDto supplierDto, ItemDto itemDto) throws SQLException;
}
