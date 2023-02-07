package lk.ijse.BackeryManagement.dao;

import lk.ijse.BackeryManagement.dto.AttendanceDTO;
import lk.ijse.BackeryManagement.dto.EmployeeDTO;
import lk.ijse.BackeryManagement.dto.ProductDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<T> tableView() throws SQLException, ClassNotFoundException;

    public boolean add(T entity) throws SQLException, ClassNotFoundException;

    public boolean delete(String Nic) throws SQLException, ClassNotFoundException;

    public boolean delete(T entity) throws SQLException, ClassNotFoundException;

    public T search(String Nic) throws SQLException, ClassNotFoundException;

    public T search(String date, String Nic) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException;

    //For Transaction Different Update names
    public boolean updateQty(T entity) throws SQLException, ClassNotFoundException;

    public boolean updateQuantity(T entity) throws SQLException, ClassNotFoundException;


    public ArrayList<String> load() throws SQLException, ClassNotFoundException;


}
