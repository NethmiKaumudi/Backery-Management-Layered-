package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dto.MaterialTableDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialUsageModel {
    public static boolean saveMaterialUsgae(ArrayList<MaterialTableDTO> materialusage) throws SQLException, ClassNotFoundException {
        for (MaterialTableDTO materialTable : materialusage) {
           // System.out.println(materialTable);
            if (!save(materialTable)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(MaterialTableDTO materialTable) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO material_usage VALUES(?, ?, ?, ?)";
      //  System.out.println(materialTable.getMaterialqty());
        return Crudutil.execute(sql,materialTable.getDate(),materialTable.getUserId(),materialTable.getId(),materialTable.getPrId());
    }


}
