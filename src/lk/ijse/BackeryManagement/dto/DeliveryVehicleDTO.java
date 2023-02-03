package lk.ijse.BackeryManagement.dto;

import java.util.ArrayList;

public class DeliveryVehicleDTO {
    private String date;
    private String vehicleNo;
    private ArrayList<DeliveryDetailsTableDTO>deliveryDetails;

    public DeliveryVehicleDTO() {
    }

    public DeliveryVehicleDTO(String date, String vehicleNo, ArrayList<DeliveryDetailsTableDTO> deliveryDetails) {
        this.date = date;
        this.vehicleNo = vehicleNo;
        this.deliveryDetails = deliveryDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public ArrayList<DeliveryDetailsTableDTO> getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(ArrayList<DeliveryDetailsTableDTO> deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    @Override
    public String toString() {
        return "DeliveryVehicle{" +
                "date='" + date + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", deliveryDetails=" + deliveryDetails +
                '}';
    }
}
