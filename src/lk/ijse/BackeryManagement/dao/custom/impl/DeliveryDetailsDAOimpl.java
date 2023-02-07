package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.CrudDAO;
import lk.ijse.BackeryManagement.dao.custom.DeliveryDetailsDAO;
import lk.ijse.BackeryManagement.dto.DeliveryDetailsTableDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDetailsDAOimpl implements DeliveryDetailsDAO {
//    public static boolean saveDeliveryDetails(ArrayList<DeliveryDetailsTableDTO> deliveryDetailsTable) throws SQLException, ClassNotFoundException {
//        for (DeliveryDetailsTableDTO deliveryDetailsTables : deliveryDetailsTable) {
//            if (!save(deliveryDetailsTables)) {
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    public ArrayList<DeliveryDetailsTableDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean add(DeliveryDetailsTableDTO deliveryDetailsTable) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO material_usage VALUES(?, ?, ?, ?)",deliveryDetailsTable.getDate(),deliveryDetailsTable.getVehicleNo(),deliveryDetailsTable.getPrId(),deliveryDetailsTable.getUnitPrice(),deliveryDetailsTable.getProductQty());
    }

    @Override
    public boolean delete(String Nic) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(DeliveryDetailsTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public DeliveryDetailsTableDTO search(String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public DeliveryDetailsTableDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(DeliveryDetailsTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQty(DeliveryDetailsTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQuantity(DeliveryDetailsTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> load() throws SQLException, ClassNotFoundException {
        return null;
    }
}
