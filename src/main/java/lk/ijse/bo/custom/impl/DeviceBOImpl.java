package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DeviceBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.DeviceDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.DeviceDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Device;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceBOImpl implements DeviceBO {

    DeviceDAO deviceDAO= (DeviceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEVICE);
    @Override
    public List<DeviceDto> getAllDevices() throws SQLException, ClassNotFoundException {
        ArrayList<DeviceDto> deviceDTOS=new ArrayList<>();
        ArrayList<Device>devices=deviceDAO.getAll();
        for (Device device:devices) {
            deviceDTOS.add(new DeviceDto(device.getDeviceId(),device.getDName(),device.getProblem(),device.getStatus(),device.getCost(),device.getDate(),device.getCId()));
        }
        return deviceDTOS;
    }

    @Override
    public List<DeviceDto> getAllDevicesByStatus(String status) throws SQLException, ClassNotFoundException {
        ArrayList<DeviceDto> deviceDTOS=new ArrayList<>();
        ArrayList<Device>devices= (ArrayList<Device>) deviceDAO.getAllDevicesByStatus(status);
        for (Device device:devices) {
            deviceDTOS.add(new DeviceDto(device.getDeviceId(),device.getDName(),device.getProblem(),device.getStatus(),device.getCost(),device.getDate(),device.getCId()));
        }
        return deviceDTOS;
    }

    @Override
    public boolean setDevice(DeviceDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean updateDevice(DeviceDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteDevice(String deviceId) throws SQLException {
        return false;
    }

    @Override
    public DeviceDto getDevice(String deviceId) throws SQLException {
        return null;
    }

    @Override
    public String generateNextDeviceId() throws SQLException {
        return null;
    }
}
