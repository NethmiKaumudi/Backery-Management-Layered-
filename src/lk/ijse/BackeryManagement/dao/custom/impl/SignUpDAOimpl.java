package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.SignUpDAO;
import lk.ijse.BackeryManagement.dto.UserDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.SQLException;
import java.util.ArrayList;

public class SignUpDAOimpl implements SignUpDAO {

    @Override
    public ArrayList<UserDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean add(UserDTO user) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO  user VALUES (?, ?, ?, ?,?)",user.getUid(),user.getUserName(),user.getPassWord(),user.getEmail(),user.getNic());
    }

    @Override
    public boolean delete(String Nic) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(UserDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserDTO search(String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(UserDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQty(UserDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQuantity(UserDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> load() throws SQLException, ClassNotFoundException {
        return null;
    }
//    public static User ConfirmUser(String uId) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT  * FROM user WHERE   user_name= ? and password=?";
//        ResultSet result = Crudutil.execute(sql, uId);
//
//        if (result.next()) {
//            return new User(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getString(4),
//                    result.getString(5)
//
//            );
//        }
//        return null;
//    }

    }
