package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class itemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");
        ArrayList<Item> getAllItems=new ArrayList<>();
        while (rst.next()){
            Item entity=new Item(rst.getString("itemId"), rst.getString("itemName"), rst.getString("qtyOnHand"), rst.getString("cost"), rst.getString("unitPrice"));
            getAllItems.add(entity);
        }
        return getAllItems;
    }
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item (itemId, itemName, qtyOnHand, cost, unitPrice) VALUES (?,?,?,?,?)",
                entity.getItemId(), entity.getItemName(), entity.getQtyOnHand(), entity.getCost(), entity.getUnitPrice());
    }
    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Update item SET itemName=?, qtyOnHand =?, cost =?, unitPrice =? WHERE itemId =?"
                ,entity.getItemName(), entity.getQtyOnHand(), entity.getCost(), entity.getUnitPrice(),entity.getItemId());

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE itemId=?",id);
    }
    @Override
    public  String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT itemId FROM item ORDER BY itemId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("itemId");
            int newCustomerId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newCustomerId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT cId FROM Customer WHERE cId=?",id);
        return resultSet.next();
    }
    @Override
    public Item search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item WHERE itemId=?",id);
        rst.next();
        return new Item(id + "", rst.getString("itemName"), rst.getString("qtyOnHand"), rst.getString("cost"), rst.getString("unitPrice"));
    }
}
