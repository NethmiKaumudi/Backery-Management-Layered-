package lk.ijse.BackeryManagement.dto;

public class VehicleDTO {
    private String VehicleNo;
    private String VehicleDetails;

    public VehicleDTO() {
    }

    public VehicleDTO(String vehicleNo, String vehicleDetails) {
        VehicleNo = vehicleNo;
        VehicleDetails = vehicleDetails;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }

    public String getVehicleDetails() {
        return VehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        VehicleDetails = vehicleDetails;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "VehicleNo='" + VehicleNo + '\'' +
                ", VehicleDetails='" + VehicleDetails + '\'' +
                '}';
    }
}
