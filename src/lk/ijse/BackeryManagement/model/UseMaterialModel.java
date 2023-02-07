package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dao.custom.impl.DeliveryDetailsDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.impl.MaterialStockDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.impl.MaterialUsageDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.impl.ProductDAOimpl;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class UseMaterialModel {

    MaterialStockDAOimpl materialStockDAOimpl = new MaterialStockDAOimpl();

    //  MaterialStockDTO materialStockDTO = new MaterialStockDTO();
    public boolean updateQty(ArrayList<MaterialTableDTO> materialDetails) throws SQLException, ClassNotFoundException {
        for (MaterialTableDTO materialTable : materialDetails) {
            if (!materialStockDAOimpl.updateQty(new MaterialStockDTO(materialTable.getId(), materialTable.getMaterialtype(), materialTable.getUserId(), materialTable.getMaterialqty()))) {
                return false;
            }
        }
        return true;
    }

    ProductDAOimpl productDAOimpl = new ProductDAOimpl();

    public boolean updateProductQty(ArrayList<MaterialTableDTO> materialdetails) throws SQLException, ClassNotFoundException {
        for (MaterialTableDTO materialTable : materialdetails) {
            if (!productDAOimpl.updateQty(new ProductDTO(materialTable.getPrId(), materialTable.getProductName(), materialTable.getUnitPrice(), materialTable.getPrductqty()))) {
                return false;
            }
        }
        return true;
    }


    MaterialUsageDAOimpl materialUsage1 = new MaterialUsageDAOimpl();

    public boolean saveMaterialUsgae(ArrayList<MaterialTableDTO> materialusage) throws SQLException, ClassNotFoundException {
        for (MaterialTableDTO materialTable : materialusage) {
            if (!materialUsage1.add(materialTable)) {
                return false;
            }
        }
        return true;
    }


    public boolean useMaterial(UseMaterialDTO useMaterial) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isProductUpdated = updateProductQty(useMaterial.getMaterialUsage());
            if (isProductUpdated) {
                boolean isUpdated = updateQty(useMaterial.getMaterialUsage());
                if (isUpdated) {
                    boolean isMaterialUsageAdded = saveMaterialUsgae(useMaterial.getMaterialUsage());
                    if (isMaterialUsageAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
