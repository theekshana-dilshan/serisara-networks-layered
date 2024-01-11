package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.DeviceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DeviceBO extends SuperBO {
    List<DeviceDto> getAllDevices() throws SQLException, ClassNotFoundException;

    List<DeviceDto> getAllDevicesByStatus(String status) throws SQLException, ClassNotFoundException;

    boolean setDevice(DeviceDto dto) throws SQLException;
    boolean updateDevice(DeviceDto dto) throws SQLException;

    boolean deleteDevice(String deviceId) throws SQLException;

    DeviceDto getDevice(String deviceId) throws SQLException;

    String generateNextDeviceId() throws SQLException;
}
