package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBOFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        CUSTOMER,DEVICE,EMPLOYEE,ITEM,ORDER,ORDER_ITEM,PAYMENT,PLACE_ORDER,SUPPLIER,SUPPLIER_ITEM,USER
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case DEVICE:
                return new DeviceBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_ITEM:
                return new OrderItemBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLIER_ITEM:
                return new SupplierItemBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
