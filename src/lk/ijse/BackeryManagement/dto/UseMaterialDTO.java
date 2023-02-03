package lk.ijse.BackeryManagement.dto;

import java.util.ArrayList;

public class UseMaterialDTO {
     private String date;
     private ArrayList<MaterialTableDTO>MaterialUsage;

    public UseMaterialDTO() {
    }

    public UseMaterialDTO(String date, ArrayList<MaterialTableDTO> materialUsage) {
        this.date = date;
        MaterialUsage = materialUsage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<MaterialTableDTO> getMaterialUsage() {
        return MaterialUsage;
    }

    public void setMaterialUsage(ArrayList<MaterialTableDTO> materialUsage) {
        MaterialUsage = materialUsage;
    }
}
