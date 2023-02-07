package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.PaymentDAO;
import lk.ijse.BackeryManagement.dto.PaymentDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOimpl implements PaymentDAO {
    @Override
    public ArrayList<PaymentDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean add(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("INSERT INTO payment VALUES (?, ?, ?, ?,?)", payment.getPaymentId(), payment.getDate(), payment.getDescription(), payment.getPrice(), payment.getId());

    }

    public  boolean delete(String pId) throws SQLException, ClassNotFoundException {
        //   System.out.println(employee.getNic());
        return Crudutil.execute("Delete from payment where  pId=?", pId);

    }

    @Override
    public boolean delete(PaymentDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  PaymentDTO search(String paymentId) throws SQLException, ClassNotFoundException {
        ResultSet rst = Crudutil.execute("SELECT  * FROM payment WHERE pId = ?", paymentId + "");
        rst.next();
        return new PaymentDTO(paymentId + "", rst.getString("date"), rst.getString("description"), rst.getDouble("price"), rst.getString("mId"));
    }

    @Override
    public PaymentDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean update(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update payment SET date  = ?,  description = ? ,price=? ,mId=? WHERE pId= ?", payment.getDate(), payment.getDescription(), payment.getPrice(), payment.getId(), payment.getPaymentId());
    }

    @Override
    public boolean updateQty(PaymentDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateQuantity(PaymentDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> load() throws SQLException, ClassNotFoundException {
        return null;
    }

}
