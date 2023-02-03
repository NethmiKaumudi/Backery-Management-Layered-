package lk.ijse.BackeryManagement.view.tm;

public class AttendanceTm {
    private String date;
    private String attendance;
    private String Nic;

    public AttendanceTm() {
    }

    public AttendanceTm(String date, String attendance, String nic) {
        this.date = date;
        this.attendance = attendance;
        Nic = nic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }
}
