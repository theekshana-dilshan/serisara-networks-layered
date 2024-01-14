package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.*;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.SupplierDto;

import java.sql.Connection;
import java.sql.SQLException;

public class ReStockBOImpl implements ReStockBO {
    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ITEM);
    SupplierItemBO supplierItemBO = (SupplierItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIER_ITEM);

    public boolean itemSupplier(SupplierDto supplierDto, ItemDto itemDto) throws SQLException {
        String supplierId = supplierDto.getSupId();
        String itemId = itemDto.getItemId();

        Connection connection = null;

        connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isUpdated = itemBO.updateItemBySupply(itemDto);
            if(isUpdated){
                boolean isSaved = supplierItemBO.saveSupplierItem(supplierId,itemId);
                if(isSaved){
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
