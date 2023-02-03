package lk.ijse.BackeryManagement.view.tm;


import javafx.scene.control.Button;

public class MaterialTableTm {

    private String date;
    private String prId;
    private String productName;
    private Double unitPrice;
    private int prductqty;
    private String Id;
    private String materialtype;
    private String userId;
    private String materialqty;
    private Button btnDelete;

    public MaterialTableTm() {
    }

    public MaterialTableTm(String date, String prId, String productName, Double unitPrice, int prductqty, String id, String materialtype, String userId, String materialqty, Button btnDelete) {
        this.date = date;
        this.prId = prId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.prductqty = prductqty;
        Id = id;
        this.materialtype = materialtype;
        this.userId = userId;
        this.materialqty = materialqty;
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

    public int getPrductqty() {
        return prductqty;
    }

    public void setPrductqty(int prductqty) {
        this.prductqty = prductqty;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMaterialqty() {
        return materialqty;
    }

    public void setMaterialqty(String materialqty) {
        this.materialqty = materialqty;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
}
