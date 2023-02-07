package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.AttendanceDAO;
import lk.ijse.BackeryManagement.dto.AttendanceDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOimpl implements AttendanceDAO {
    @Override
    public ArrayList<AttendanceDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(AttendanceDTO attendance) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO attendance VALUES (?, ?, ?)", attendance.getDate(), attendance.getAttendance(), attendance.getNic());

    }

    @Override
    public boolean delete(String Nic) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AttendanceDTO search(String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(AttendanceDTO attendance) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Delete from attendance where date=? and nIc=?", attendance.getDate(), attendance.getNic());

    }

    @Override
    public AttendanceDTO search(String date,String Nic) throws SQLException, ClassNotFoundException {
        ResultSet rst = Crudutil.execute("SELECT  * FROM attendance WHERE date=? and nIc=?",date,Nic+"");
        rst.next();
        return new AttendanceDTO(date, rst.getString("attendance"),Nic+"");
    }

    @Override
    public boolean update(AttendanceDTO attendance) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update attendance SET attendance=? WHERE date= ? and nIc=?", attendance.getAttendance(), attendance.getDate(), attendance.getNic());
    }

    @Override
    public boolean updateQty(AttendanceDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQuantity(AttendanceDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> load() throws SQLException, ClassNotFoundException {
        return null;
    }
}
