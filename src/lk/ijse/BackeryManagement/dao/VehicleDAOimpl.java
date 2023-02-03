package lk.ijse.BackeryManagement.dao;

import lk.ijse.BackeryManagement.dto.VehicleDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDAOimpl {
    public static boolean addVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO vehicle VALUES (?, ?)", vehicle.getVehicleNo(), vehicle.getVehicleDetails());

    }
    public static boolean updateVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update vehicle SET details= ? WHERE vNo= ?",vehicle.getVehicleDetails(),vehicle.getVehicleNo());

    }
//    public static boolean deleteVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
//        System.out.println(vehicle.getVehicleNo());
//        String sql = "Delete from vehicle where vNo=?";
//        Object execute = Crudutil.execute(sql, vehicle.getVehicleNo());
//        System.out.println(execute);
//
//        return (boolean) execute;
//
//    }
//    public static VehicleDTO searchvehicle(String  VehicleNo) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT  * FROM vehicle WHERE vNo = ?";
//        ResultSet result = Crudutil.execute(sql, VehicleNo);
//
//        if (result.next()) {
//            return new VehicleDTO(
//                    result.getString(1),
//                    result.getString(2)
//            );
//        }
//        return null;
//    }
}
