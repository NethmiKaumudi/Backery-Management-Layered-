package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.MaterialUsageDAO;
import lk.ijse.BackeryManagement.dto.MaterialTableDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialUsageDAOimpl implements MaterialUsageDAO {
    @Override
    public ArrayList<MaterialTableDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    //    public  boolean saveMaterialUsgae(ArrayList<MaterialTableDTO> materialusage) throws SQLException, ClassNotFoundException {
//        for (MaterialTableDTO materialTable : materialusage) {
//            if (!save(materialTable)) {
//                return false;
//            }
//        }
//        return true;
//    }
   public boolean add(MaterialTableDTO materialTable) throws SQLException, ClassNotFoundException {
        //  System.out.println(materialTable.getMaterialqty());
        return Crudutil.execute("INSERT INTO material_usage VALUES(?, ?, ?, ?)", materialTable.getDate(), materialTable.getUserId(), materialTable.getId(), materialTable.getPrId());
    }

    @Override
    public boolean delete(String Nic) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(MaterialTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public MaterialTableDTO search(String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public MaterialTableDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(MaterialTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQty(MaterialTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQuantity(MaterialTableDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> load() throws SQLException, ClassNotFoundException {
        return null;
    }
}
