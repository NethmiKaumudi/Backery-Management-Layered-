package lk.ijse.BackeryManagement.view.tm;

public class EmployeeTM {
     private String Nic;
     private String Name;
     private String Address;
     private String LicenceNo;
     private int Contact;
     private String Position;
     private Double BasicSalary;

    public EmployeeTM() {
    }

    public EmployeeTM(String nic, String name, String address, String licenceNo, int contact, String position, Double basicSalary) {
        Nic = nic;
        Name = name;
        Address = address;
        LicenceNo = licenceNo;
        Contact = contact;
        Position = position;
        BasicSalary = basicSalary;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLicenceNo() {
        return LicenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        LicenceNo = licenceNo;
    }

    public int getContact() {
        return Contact;
    }

    public void setContact(int contact) {
        Contact = contact;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }


    public Double getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        BasicSalary = basicSalary;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "Nic='" + Nic + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", LicenceNo='" + LicenceNo + '\'' +
                ", Contact=" + Contact +
                ", Position='" + Position + '\'' +
                ", BasicSalary=" + BasicSalary +
                '}';
    }
}
