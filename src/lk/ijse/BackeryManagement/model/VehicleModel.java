package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dto.VehicleDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {
//    public static boolean addVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO vehicle VALUES (?, ?)";
//        return Crudutil.execute(sql, vehicle.getVehicleNo(), vehicle.getVehicleDetails());
//
//    }

    public static boolean deleteVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        System.out.println(vehicle.getVehicleNo());
        String sql = "Delete from vehicle where vNo=?";
        Object execute = Crudutil.execute(sql, vehicle.getVehicleNo());
        System.out.println(execute);

        return (boolean) execute;

    }
    public static VehicleDTO searchvehicle(String  VehicleNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM vehicle WHERE vNo = ?";
        ResultSet result = Crudutil.execute(sql, VehicleNo);

        if (result.next()) {
            return new VehicleDTO(
                    result.getString(1),
                    result.getString(2)
            );
        }
        return null;
    }
//    public static boolean updateVehicle(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
//        String sql ="Update vehicle SET details= ? WHERE vNo= ?";
//        return Crudutil.execute(sql,vehicle.getVehicleDetails(),vehicle.getVehicleNo());
//
//    }
//    String sql ="Update product SET product_name = ?,unit_price = ? ,Quantity=? WHERE prId= ?";
//        return Crudutil.execute(sql,product.getProductName(),product.getUnitPrice(),product.getQuantity(),product.getPrid());

    public static ArrayList<String> loadVehicleNos() throws SQLException, ClassNotFoundException {
        String sql = "SELECT vNo FROM vehicle";
        ResultSet result = Crudutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
