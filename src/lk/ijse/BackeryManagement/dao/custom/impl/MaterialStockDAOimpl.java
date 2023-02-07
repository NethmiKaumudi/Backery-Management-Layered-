package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.MaterialStockDAO;
import lk.ijse.BackeryManagement.dto.MaterialStockDTO;
import lk.ijse.BackeryManagement.dto.MaterialTableDTO;
import lk.ijse.BackeryManagement.model.UseMaterialModel;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialStockDAOimpl implements MaterialStockDAO {
    public  MaterialStockDTO search(String mId) throws SQLException, ClassNotFoundException {
        ResultSet rst = Crudutil.execute("SELECT  * FROM material_stock WHERE mId= ?", mId + "");
        rst.next();
        return new MaterialStockDTO(mId + "", rst.getString("material_type"), rst.getString("quantity"), rst.getString("uId"));
    }

    @Override
    public MaterialStockDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(MaterialStockDTO materialStock) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update material_stock SET  material_type =?, quantity=?, uId=? WHERE mId=?", materialStock.getMaterialType(), materialStock.getQuantity(), materialStock.getUid(), materialStock.getMid());

    }

    @Override
    public ArrayList<MaterialStockDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean add(MaterialStockDTO materialStock) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO material_stock  VALUES (?, ?, ?, ?)", materialStock.getMid(), materialStock.getMaterialType(), materialStock.getQuantity(), materialStock.getUid());

    }

    public  boolean delete(String Mid) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Delete from material_stock where  mId=?", Mid);
    }

    @Override
    public boolean delete(MaterialStockDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

//    public  MaterialStockDTO search(String Id) throws SQLException, ClassNotFoundException {
//        ResultSet rst = Crudutil.execute("SELECT  * FROM material_stock WHERE mId = ?", Id + "");
//        rst.next();
//        return new MaterialStockDTO(Id + "", rst.getString("material_type"), rst.getString("quantity"), rst.getString("uId"));
//
//    }
    //

    public  ArrayList<String> load() throws SQLException, ClassNotFoundException {
        String sql = "SELECT mId FROM material_stock ";
        ResultSet result = Crudutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }

//    public  boolean updateQty(ArrayList<MaterialTableDTO> materialDetails) throws SQLException, ClassNotFoundException {
//        for (MaterialTableDTO materialTable : materialDetails) {
//            if (!updateQty(new MaterialStockDTO(materialTable.getId(), materialTable.getMaterialtype(), materialTable.getUserId(), materialTable.getMaterialqty()))) {
//                return false;
//            }
//        }
//        return true;
//    }
//Transaction Update
    public boolean updateQty(MaterialStockDTO materialStock) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE material_stock SET quantity = quantity - ? WHERE  mId = ?";
        return Crudutil.execute(sql, materialStock.getQuantity(), materialStock.getMid());
    }

    @Override
    public boolean updateQuantity(MaterialStockDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

}
