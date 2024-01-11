package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory{
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDER_ITEM,DEVICE,EMPLOYEE,PAYMENT,QUERY,SUPPLIER,SUPPLIER_ITEM,USER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_ITEM:
                return new OrderItemDAOImpl();
            case DEVICE:
                return new DeviceDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_ITEM:
                return new SupplierItemDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
