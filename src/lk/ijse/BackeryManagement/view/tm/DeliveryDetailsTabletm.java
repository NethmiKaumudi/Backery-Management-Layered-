package lk.ijse.BackeryManagement.view.tm;


import javafx.scene.control.Button;

public class DeliveryDetailsTabletm {
    private String date;
    private String vehicleNo;
    private String prId;
    private String productName;
    private Double unitPrice;
    private int productQty;
    private Button btnDelete;

    public DeliveryDetailsTabletm() {
    }

    public DeliveryDetailsTabletm(String date, String vehicleNo, String prId, String productName, Double unitPrice, int productQty, Button btnDelete) {
        this.date = date;
        this.setVehicleNo(vehicleNo);
        this.prId = prId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.productQty = productQty;
        this.btnDelete = btnDelete;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }


    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
}
