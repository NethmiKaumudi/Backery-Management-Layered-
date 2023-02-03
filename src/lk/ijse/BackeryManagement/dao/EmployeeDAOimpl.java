package lk.ijse.BackeryManagement.dao;

import lk.ijse.BackeryManagement.dto.EmployeeDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOimpl {
    public static boolean addEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO employee VALUES (?, ?, ?, ?,?,?,?)", employee.getNic(), employee.getName(), employee.getAddress(), employee.getLicenceNo(), employee.getContact(), employee.getPosition(), employee.getBasicSalary());

    }

    public static boolean deleteEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        System.out.println(employeeDTO.getNic());
        return Crudutil.execute("Delete from employee where nIc=?", employeeDTO.getNic());

    }

    public static EmployeeDTO searchEmployee(String Nic) throws SQLException, ClassNotFoundException {
        ResultSet rst = Crudutil.execute("SELECT  * FROM employee WHERE nIc = ?", Nic + "");
        rst.next();
        return new EmployeeDTO(Nic + "", rst.getString("name"), rst.getString("address"), rst.getString("vehicle_licance_number"), rst.getInt("contact"), rst.getString("position"), rst.getDouble("basic_salary"));
    }

    public static boolean updateEmployee(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update employee SET name  = ?, address = ? ,vehicle_licance_number=? ,contact=? ,position=?, basic_salary=? WHERE nIc= ?", employee.getName(), employee.getAddress(), employee.getLicenceNo(), employee.getContact(), employee.getPosition(), employee.getBasicSalary(), employee.getNic());

    }

//    public static ArrayList<String> loadEmployeeNics() throws SQLException, ClassNotFoundException {
//        ArrayList<String> idList = new ArrayList<>();
//        ResultSet result = Crudutil.execute("SELECT nIc FROM employee");
//        while (result.next()) {
//            idList.add(result.getString(1));
//        }
//        return idList;
//    }
    //
public static ArrayList<String> loadEmployeeNics() throws SQLException, ClassNotFoundException {
    String sql = "SELECT nIc FROM employee";
    ResultSet result = Crudutil.execute(sql);

    ArrayList<String> idList = new ArrayList<>();

    while (result.next()) {
        idList.add(result.getString(1));
    }
    return idList;
}
}
