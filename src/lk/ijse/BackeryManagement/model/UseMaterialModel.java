package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dao.MaterialStockDAOimpl;
import lk.ijse.BackeryManagement.dao.ProductDAOimpl;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.dto.UseMaterialDTO;

import java.sql.SQLException;

public class UseMaterialModel {

public static boolean useMaterial(UseMaterialDTO useMaterial) throws SQLException, ClassNotFoundException {
    try {
        DBConnection.getInstance().getConnection().setAutoCommit(false);
        boolean isProductUpdated = ProductDAOimpl.updateProductQty(useMaterial.getMaterialUsage());
     if (isProductUpdated) {
          boolean isUpdated = MaterialStockDAOimpl.updateQty(useMaterial.getMaterialUsage());
          if (isUpdated) {
              boolean isMaterialUsageAdded = MaterialUsageModel.saveMaterialUsgae(useMaterial.getMaterialUsage());
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
