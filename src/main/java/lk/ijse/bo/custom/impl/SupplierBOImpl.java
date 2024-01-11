package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.SupplierDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDto> supplierDtos=new ArrayList<>();
        ArrayList<Supplier>suppliers=supplierDAO.getAll();
        for (Supplier supplier:suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSupId(),supplier.getSName(),supplier.getAddress(),supplier.getContact()));
        }
        return supplierDtos;
    }

    @Override
    public boolean setSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getSupId(),dto.getSName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupId(),dto.getSName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean deleteSupplier(String supId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supId);
    }

    @Override
    public SupplierDto getSupplier(String supId) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.getSupplier(supId);
        SupplierDto dto = new SupplierDto(supplier.getSupId(), supplier.getSName(), supplier.getAddress(), supplier.getContact());
        return dto;
    }

    @Override
    public SupplierDto getSupplierByName(String name) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.getSupplierByName(name);
        SupplierDto dto = new SupplierDto(supplier.getSupId(), supplier.getSName(), supplier.getAddress(), supplier.getContact());
        return dto;
    }

    @Override
    public String generateNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewId();
    }
}
