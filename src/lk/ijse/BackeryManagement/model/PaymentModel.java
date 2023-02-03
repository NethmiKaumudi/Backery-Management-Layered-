package lk.ijse.BackeryManagement.model;

import lk.ijse.BackeryManagement.dto.PaymentDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {
    public static boolean addPayment(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment VALUES (?, ?, ?, ?,?)";
        return Crudutil.execute(sql,payment.getPaymentId(),payment.getDate(),payment.getDescription(),payment.getPrice(),payment.getId());

    }
    public static boolean deletePayment(PaymentDTO payment) throws SQLException, ClassNotFoundException {
     //   System.out.println(employee.getNic());
        String sql="Delete from payment where  pId=?";
        Object execute = Crudutil.execute(sql, payment.getPaymentId());
        System.out.println(execute);

        return (boolean) execute;

    }
    public static PaymentDTO searchPayment(String paymentId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM payment WHERE pId = ?";
        ResultSet result = Crudutil.execute(sql, paymentId);

        if (result.next()) {
            return new PaymentDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4),
                    result.getString(5)

            );
        }
        return null;
    }
    public static boolean updatePayment(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        String sql ="Update payment SET date  = ?,  description = ? ,price=? ,mId=? WHERE pId= ?";
        return Crudutil.execute(sql,payment.getDate(),payment.getDescription(),payment.getPrice(),payment.getId(),payment.getPaymentId());

    }



}
