package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dto.PayrollDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.SQLException;

public class PayrollModel {
    public static boolean addPayroll(PayrollDTO payroll) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payroll VALUES (?, ?,?,?,?,?,?)";
        return Crudutil.execute(sql,payroll.getNic(),payroll.getMonthYear(),payroll.getBasicSalary(),payroll.getEmployeeEPF(),payroll.getMonthlySalary(),payroll.getEmployerEPF(),payroll.getEmployerETF());

    }
    public static boolean deletePayroll(PayrollDTO payroll) throws SQLException, ClassNotFoundException {

        String sql="Delete from payroll where nIc=? and month_year=?";
        Object execute = Crudutil.execute(sql, payroll.getNic(),payroll.getMonthYear());
        System.out.println(execute);

        return (boolean) execute;

    }

}
