package lk.ijse.BackeryManagement.dao;

import lk.ijse.BackeryManagement.dto.UserDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.SQLException;

public class SignUpDAOimpl {

    public static boolean signinUser(UserDTO user) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO  user VALUES (?, ?, ?, ?,?)",user.getUid(),user.getUserName(),user.getPassWord(),user.getEmail(),user.getNic());
    }
  //  public static User ConfirmUser(String uId) throws SQLException, ClassNotFoundException {
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
