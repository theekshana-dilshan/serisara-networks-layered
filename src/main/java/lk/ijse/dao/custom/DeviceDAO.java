package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.DeviceDto;
import lk.ijse.entity.Device;

import java.sql.SQLException;
import java.util.List;

public interface DeviceDAO extends CrudDAO<Device> {
    List<Device> getAllDevicesByStatus(String status) throws SQLException, ClassNotFoundException;
}
