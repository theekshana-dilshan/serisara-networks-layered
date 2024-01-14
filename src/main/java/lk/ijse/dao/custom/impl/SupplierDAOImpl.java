package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dto.SupplierDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> getAllSupplier=new ArrayList<>();
        while (rst.next()){
            Supplier entity=new Supplier(rst.getString("supId"), rst.getString("sName"), rst.getString("address"), rst.getString("contact"));
            getAllSupplier.add(entity);
        }
        return getAllSupplier;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier (supId,sName, address, contact) VALUES (?,?,?,?)",
                entity.getSupId(), entity.getSName(), entity.getAddress(), entity.getContact());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET sName=?, address=?, contact=? WHERE supId=?"
                ,entity.getSName(), entity.getAddress(), entity.getContact(), entity.getSupId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supId=?",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT supId FROM supplier ORDER BY supId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("supId");
            int newSupplierId = Integer.parseInt(id.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newSupplierId);
        } else {
            return "S00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT supId FROM supplier WHERE supId=?",id);
        return resultSet.next();
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE supId=?",id);
        rst.next();
        return new Supplier(id + "", rst.getString("sName"), rst.getString("address"), rst.getString("contact"));
    }

    @Override
    public Supplier getSupplier(String supId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE supId=?",supId);
        rst.next();
        return new Supplier(supId + "", rst.getString("sName"), rst.getString("address"), rst.getString("contact"));
    }

    @Override
    public Supplier getSupplierByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE sName=?",name);
        rst.next();
        return new Supplier(rst.getString("supId"), rst.getString("sName"), rst.getString("address"), rst.getString("contact"));
    }

    @Override
    public boolean saveSupplierItem(String itemId, String supId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO itemSupplierDetail (itemId, supId) VALUES (?,?)",itemId, supId);
    }
}
