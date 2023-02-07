package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.VehicleDAO;
import lk.ijse.BackeryManagement.dto.VehicleDTO;
import lk.ijse.BackeryManagement.util.Crudutil;
import org.apache.commons.lang.Validate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOimpl implements VehicleDAO {
    @Override
    public ArrayList<VehicleDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean add(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO vehicle VALUES (?, ?)", vehicle.getVehicleNo(), vehicle.getVehicleDetails());

    }

    public  boolean update(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update vehicle SET details= ? WHERE vNo= ?", vehicle.getVehicleDetails(), vehicle.getVehicleNo());

    }

    @Override
    public boolean updateQty(VehicleDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQuantity(VehicleDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  boolean delete(String VehicleNo) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Delete from vehicle where vNo=?", VehicleNo);

    }

    @Override
    public boolean delete(VehicleDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  VehicleDTO search(String  VehicleNo) throws SQLException, ClassNotFoundException {
        ResultSet rst = Crudutil.execute("SELECT  * FROM vehicle WHERE vNo = ?", VehicleNo+"");
        rst.next();
        return new VehicleDTO(VehicleNo+"", rst.getString("details"));
    }

    @Override
    public VehicleDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    //
    public  ArrayList<String> load() throws SQLException, ClassNotFoundException {
        String sql = "SELECT vNo FROM vehicle";
        ResultSet result = Crudutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
