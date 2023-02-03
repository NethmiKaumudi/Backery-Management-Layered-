package lk.ijse.BackeryManagement.dto;

public class MaterialTableDTO {
    private String date;
    private String prId;
    private String productName;
    private Double unitPrice;
    private int prductqty;
    private String Id;
    private String materialtype;
    private String userId;
    private String materialqty;


    public MaterialTableDTO() {
    }

    public MaterialTableDTO(String date, String prId, String productName, Double unitPrice, int prductqty, String id, String materialtype, String userId, String materialqty) {
        this.date = date;
        this.prId = prId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.prductqty = prductqty;
       this.Id = id;
        this.materialtype = materialtype;
        this.userId = userId;
        this.materialqty = materialqty;
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

    @Override
    public String toString() {
        return "MaterialTable{" +
                "date='" + date + '\'' +
                ", prId='" + prId + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", prductqty=" + prductqty +
                ", Id='" + Id + '\'' +
                ", materialtype='" + materialtype + '\'' +
                ", userId='" + userId + '\'' +
                ", materialqty='" + materialqty + '\'' +
                '}';
    }
}
