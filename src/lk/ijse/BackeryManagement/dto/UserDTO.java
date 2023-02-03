package lk.ijse.BackeryManagement.dto;

public class UserDTO {
    private String Uid;
    private String UserName;
    private String PassWord;
    private String Email;
    private String Nic;

    public UserDTO() {
    }

    public UserDTO(String uid, String userName, String passWord, String email, String nic) {
        Uid = uid;
        UserName = userName;
        PassWord = passWord;
        Email = email;
        Nic = nic;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }


    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    @Override
    public String toString() {
        return "User{" +
                "Uid='" + Uid + '\'' +
                ", UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                ", Email='" + Email + '\'' +
                ", Nic='" + Nic + '\'' +
                '}';
    }
}
