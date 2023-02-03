package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dto.AttendanceDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceModel {
    public static boolean addAttendance(AttendanceDTO attendance) throws SQLException, ClassNotFoundException {
       // String sql = "INSERT INTO attendance VALUES (?, ?, ?)";
        return Crudutil.execute("INSERT INTO attendance VALUES (?, ?, ?)",attendance.getDate(),attendance.getAttendance(),attendance.getNic());

    }

    public static boolean deleteAttendance(AttendanceDTO attendance) throws SQLException, ClassNotFoundException {

        String sql="Delete from attendance where date=? and nIc=?";
        Object execute = Crudutil.execute(sql,attendance.getDate(),attendance.getNic());
        System.out.println(execute);

        return (boolean) execute;

    }
    public static AttendanceDTO searchAttendance(String date, String nIc) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM attendance WHERE date=? and nIc=?";
        ResultSet result = Crudutil.execute(sql,date,nIc);

        if (result.next()) {
            return new AttendanceDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)
            );
        }
        return null;
    }

    public static boolean updateAttendance(AttendanceDTO attendance) throws SQLException, ClassNotFoundException {
        String sql ="Update attendance SET attendance=? WHERE date= ? and nIc=?";
        return Crudutil.execute(sql,attendance.getAttendance(),attendance.getDate(),attendance.getNic());

    }
}
