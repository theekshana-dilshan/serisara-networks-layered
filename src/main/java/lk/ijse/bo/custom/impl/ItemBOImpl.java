package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.OrderTm;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto>itemDtos=new ArrayList<>();
        ArrayList<Item>items=itemDAO.getAll();
        for (Item item:items) {
            itemDtos.add(new ItemDto(item.getItemId(),item.getItemName(),item.getQtyOnHand(),item.getCost(),item.getUnitPrice()));
        }
        return itemDtos;
    }

    @Override
    public boolean setItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemId(),dto.getItemName(),dto.getQtyOnHand(),dto.getCost(),dto.getUnitPrice()));
    }

    @Override
    public boolean updateItemDto(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItemId(),dto.getItemName(),dto.getQtyOnHand(),dto.getCost(),dto.getUnitPrice()));
    }

    @Override
    public boolean updateItemBySupply(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.updateItemBySupply(dto);
    }

    @Override
    public boolean updateItem(List<OrderTm> orderTmList) throws SQLException, ClassNotFoundException {
        return itemDAO.updateItem(orderTmList);
    }

    @Override
    public boolean updateQty(String code, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQty(code, qty);
    }

    @Override
    public boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(itemId);
    }

    @Override
    public ItemDto getItem(String itemName) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getItem(itemName);
        ItemDto dto = new ItemDto(item.getItemId(), item.getItemName(), item.getQtyOnHand(), item.getCost(), item.getUnitPrice());
        return dto;
    }

    @Override
    public ItemDto getItemById(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getItemById(id);
        ItemDto dto = new ItemDto(item.getItemId(), item.getItemName(), item.getQtyOnHand(), item.getCost(), item.getUnitPrice());
        return dto;
    }

    @Override
    public String generateNextItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }
}
