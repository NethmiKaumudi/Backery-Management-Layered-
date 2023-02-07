package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dao.custom.impl.DeliveryDetailsDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.impl.ProductDAOimpl;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.dto.DeliveryDetailsTableDTO;
import lk.ijse.BackeryManagement.dto.DeliveryVehicleDTO;
import lk.ijse.BackeryManagement.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryVehicleModel {
    ProductDAOimpl productDAOimpl=new ProductDAOimpl();
    public  boolean updateProductQuantity(ArrayList<DeliveryDetailsTableDTO> deliverydetails) throws SQLException, ClassNotFoundException {
        for (DeliveryDetailsTableDTO deliveryDetailsTable: deliverydetails) {
            if (!productDAOimpl.updateQuantity(new ProductDTO(deliveryDetailsTable.getPrId(),deliveryDetailsTable.getProductName(),deliveryDetailsTable.getUnitPrice(),deliveryDetailsTable.getProductQty()))) {
                return false;
            }
        }
        return true;
    }
    DeliveryDetailsDAOimpl deliveryDetailsDAOimpl=new DeliveryDetailsDAOimpl();
    public  boolean saveDeliveryDetails(ArrayList<DeliveryDetailsTableDTO> deliveryDetailsTable) throws SQLException, ClassNotFoundException {
        for (DeliveryDetailsTableDTO deliveryDetailsTables : deliveryDetailsTable) {
            if (!deliveryDetailsDAOimpl.add(deliveryDetailsTables)) {
                return false;
            }
        }
        return true;
    }

    public boolean deliveryVehicles(DeliveryVehicleDTO deliveryVehicle) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isUpdated = updateProductQuantity(deliveryVehicle.getDeliveryDetails());
            if (isUpdated) {
                boolean isdeliveryDetailAdded = saveDeliveryDetails(deliveryVehicle.getDeliveryDetails());
                if (isdeliveryDetailAdded) {
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            // }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
