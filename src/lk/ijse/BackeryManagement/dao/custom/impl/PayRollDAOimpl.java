package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.PayRollDAO;
import lk.ijse.BackeryManagement.dto.PayrollDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.SQLException;
import java.util.ArrayList;

public class PayRollDAOimpl implements PayRollDAO {
    @Override
    public ArrayList<PayrollDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean add(PayrollDTO payroll) throws SQLException, ClassNotFoundException {

        return Crudutil.execute("INSERT INTO payroll VALUES (?, ?,?,?,?,?,?)", payroll.getNic(), payroll.getMonthYear(), payroll.getBasicSalary(), payroll.getEmployeeEPF(), payroll.getMonthlySalary(), payroll.getEmployerEPF(), payroll.getEmployerETF());

    }

    @Override
    public boolean delete(String Nic) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  boolean delete(PayrollDTO payroll) throws SQLException, ClassNotFoundException {


        return Crudutil.execute("Delete from payroll where nIc=? and month_year=?", payroll.getNic(), payroll.getMonthYear());


    }

    @Override
    public PayrollDTO search(String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PayrollDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(PayrollDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQty(PayrollDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> load() throws SQLException, ClassNotFoundException {
        return null;
    }
}
