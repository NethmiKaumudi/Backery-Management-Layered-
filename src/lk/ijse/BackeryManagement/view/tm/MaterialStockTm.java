package lk.ijse.BackeryManagement.view.tm;

public class MaterialStockTm {
    private String Mid;
    private String MaterialType;
    private String Quantity;
    private String Uid;

    public MaterialStockTm() {
    }

    public MaterialStockTm(String mid, String materialType, String quantity, String uid) {
        Mid = mid;
        MaterialType = materialType;
        Quantity = quantity;
        Uid = uid;
    }

    public String getMid() {
        return Mid;
    }

    public void setMid(String mid) {
        Mid = mid;
    }

    public String getMaterialType() {
        return MaterialType;
    }

    public void setMaterialType(String materialType) {
        MaterialType = materialType;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
