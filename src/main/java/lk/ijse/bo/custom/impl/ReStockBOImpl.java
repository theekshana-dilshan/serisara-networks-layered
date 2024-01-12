package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReStockBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.SupplierDto;
import lk.ijse.model.ItemModel;
import lk.ijse.model.SupplierItemModel;

import java.sql.Connection;
import java.sql.SQLException;

public class ReStockBOImpl implements ReStockBO {
    public boolean itemSupplier(SupplierDto supplierDto, ItemDto itemDto) throws SQLException {
        String supplierId = supplierDto.getSupId();
        String itemId = itemDto.getItemId();

        Connection connection = null;

        connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isUpdated = ItemModel.updateItemBySupply(itemDto);
            if(isUpdated){
                boolean isSaved = SupplierItemModel.saveSupplierItem(supplierId,itemId);
                if(isSaved){
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
