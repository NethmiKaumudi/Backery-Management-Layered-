package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.EmployeeDAO;
import lk.ijse.BackeryManagement.dto.EmployeeDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public ArrayList<EmployeeDTO> tableView() throws SQLException, ClassNotFoundException {


        ResultSet rst = Crudutil.execute("SELECT * From employee  WHERE  nIc LIKE ?||name LIKE ?||address LIKE ?||vehicle_licance_number LIKE ?||contact LIKE ?||position LIKE ?||basic_salary LIKE ?");
        //  Connection connection = DBConnection.getInstance().getConnection();
        //  String sql = "SELECT * From employee";
        //   String sql = "SELECT * From employee  WHERE  nIc LIKE ?||name LIKE ?||address LIKE ?||vehicle_licance_number LIKE ?||contact LIKE ?||position LIKE ?||basic_salary LIKE ?";
        ArrayList<EmployeeDTO> employee = new ArrayList<>();
        while (rst.next()) {
            employee.add(new EmployeeDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6), rst.getDouble(7)));
        }
        return employee;
    }

    public boolean add(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO employee VALUES (?, ?, ?, ?,?,?,?)", employee.getNic(), employee.getName(), employee.getAddress(), employee.getLicenceNo(), employee.getContact(), employee.getPosition(), employee.getBasicSalary());

    }

    public boolean delete(String Nic) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Delete from employee where nIc=?", Nic);

    }

    @Override
    public boolean delete(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    public EmployeeDTO search(String Nic) throws SQLException, ClassNotFoundException {
        ResultSet rst = Crudutil.execute("SELECT  * FROM employee WHERE nIc = ?", Nic + "");
        rst.next();
        return new EmployeeDTO(Nic + "", rst.getString("name"), rst.getString("address"), rst.getString("vehicle_licance_number"), rst.getInt("contact"), rst.getString("position"), rst.getDouble("basic_salary"));
    }

    @Override
    public EmployeeDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update employee SET name  = ?, address = ? ,vehicle_licance_number=? ,contact=? ,position=?, basic_salary=? WHERE nIc= ?", employee.getName(), employee.getAddress(), employee.getLicenceNo(), employee.getContact(), employee.getPosition(), employee.getBasicSalary(), employee.getNic());

    }

    @Override
    public boolean updateQty(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQuantity(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        return false;
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
    public ArrayList<String> load() throws SQLException, ClassNotFoundException {
        ResultSet result = Crudutil.execute("SELECT nIc FROM employee");

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
//    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
//        ArrayList<Item> allItems = new ArrayList<>();
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
//        while (rst.next()) {
//            allItems.add(new Item(rst.getString("code"), rst.getString("description"), rst.getInt("qtyOnHand"),rst.getBigDecimal("unitPrice")));
//        }
//        return allItems;
//    }
}
