package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.DeviceDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Device;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeviceDAOImpl implements DeviceDAO {
    @Override
    public ArrayList<Device> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM handOverDevice");
        ArrayList<Device> getAllDevice=new ArrayList<>();
        while (rst.next()){
            Device entity=new Device(rst.getString("deviceId"), rst.getString("dName"), rst.getString("problem"), rst.getString("status"), rst.getString("cost"), rst.getDate("date").toLocalDate(), rst.getString("cId"));
            getAllDevice.add(entity);
        }
        return getAllDevice;
    }

    @Override
    public boolean save(Device entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO handOverDevice (deviceId, dName, problem, status, cost, date, cId) VALUES (?,?,?,?,?,?,?)",
                entity.getDeviceId(), entity.getDName(), entity.getProblem(), entity.getStatus(), entity.getCost(), entity.getDate(), entity.getCId());
    }

    @Override
    public boolean update(Device entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE handOverDevice SET dName=?, problem=?, status=?, cost=? WHERE deviceId=?"
                ,entity.getDName(), entity.getProblem(), entity.getStatus(), entity.getCost(),entity.getDeviceId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM handOverDevice WHERE deviceId=?",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT deviceId FROM handOverDevice ORDER BY deviceId DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("deviceId");
            int newDeviceId = Integer.parseInt(id.replace("D00-", "")) + 1;
            return String.format("D00-%03d", newDeviceId);
        } else {
            return "D00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT deviceId FROM handOverDevice WHERE deviceId=?",id);
        return resultSet.next();
    }

    @Override
    public Device search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM handOverDevice WHERE deviceId=?",id);
        rst.next();
        return new Device(id + "", rst.getString("dName"), rst.getString("problem"), rst.getString("status"), rst.getString("cost"), rst.getDate("date").toLocalDate(), rst.getString("cId"));
    }
}
