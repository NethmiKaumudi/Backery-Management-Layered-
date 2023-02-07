package lk.ijse.BackeryManagement.dao.custom.impl;

import lk.ijse.BackeryManagement.dao.custom.ProductDAO;
import lk.ijse.BackeryManagement.dto.DeliveryDetailsTableDTO;
import lk.ijse.BackeryManagement.dto.MaterialTableDTO;
import lk.ijse.BackeryManagement.dto.ProductDTO;
import lk.ijse.BackeryManagement.util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOimpl implements ProductDAO {
    public  ProductDTO search(String prId) throws SQLException, ClassNotFoundException {
        ResultSet rst = Crudutil.execute("SELECT  * FROM product WHERE prId= ?", prId+"");
        rst.next();
        return new ProductDTO(prId+"",rst.getString("product_name"),rst.getDouble("unit_price"),rst.getInt("Quantity"));

    }

    @Override
    public ProductDTO search(String date, String Nic) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ProductDTO> tableView() throws SQLException, ClassNotFoundException {
        return null;
    }

    // public static ProductDTO searchProduct(String prId) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT  * FROM product WHERE prId = ?";
//        ResultSet result = Crudutil.execute(sql, prId);
//
//        if (result.next()) {
//            return new ProductDTO(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getDouble(3),
//                    result.getInt(4)
//
//            );
//        }
//        return null;
//    }
   public  boolean add(ProductDTO product) throws SQLException, ClassNotFoundException {
       return Crudutil.execute( "INSERT INTO product VALUES (?, ?, ?, ?)", product.getPrid(), product.getProductName(), product.getUnitPrice(),product.getQuantity());
   }
    public  boolean delete(String Prid) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Delete from product where prId=?",Prid);

    }

    @Override
    public boolean delete(ProductDTO entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  boolean update(ProductDTO product) throws SQLException, ClassNotFoundException {
        return Crudutil.execute("Update product SET product_name = ?,unit_price = ? ,Quantity=? WHERE prId= ?",product.getProductName(),product.getUnitPrice(),product.getQuantity(),product.getPrid());

    }
    //Material Usage------>ekatu karanna na
//    public  boolean updateProductQty(ArrayList<MaterialTableDTO> materialdetails) throws SQLException, ClassNotFoundException {
//        for (MaterialTableDTO materialTable: materialdetails) {
//            if (!updateProductQty(new ProductDTO(materialTable.getPrId(),materialTable.getProductName(),materialTable.getUnitPrice(),materialTable.getPrductqty()))) {
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean updateQty(ProductDTO product) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE product SET  Quantity =  Quantity + ? WHERE   prId = ?";
        return Crudutil.execute(sql, product.getQuantity(),product.getPrid());
    }

    //Deliverdetails --->adu karanna ona
//    public  boolean updateProductQuantity(ArrayList<DeliveryDetailsTableDTO>deliverydetails) throws SQLException, ClassNotFoundException {
//        for (DeliveryDetailsTableDTO deliveryDetailsTable: deliverydetails) {
//            if (!updateProductQuantity(new ProductDTO(deliveryDetailsTable.getPrId(),deliveryDetailsTable.getProductName(),deliveryDetailsTable.getUnitPrice(),deliveryDetailsTable.getProductQty()))) {
//                return false;
//            }
//        }
//        return true;
//    }

    public boolean updateQuantity(ProductDTO product) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE product SET  Quantity =  Quantity - ? WHERE   prId = ?";
        return Crudutil.execute(sql, product.getQuantity(),product.getPrid());
    }
    //Material Usage Form Load Product Ids

    public  ArrayList<String> load() throws SQLException, ClassNotFoundException {
        String sql = "SELECT prId FROM product";
        ResultSet result = Crudutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
    //Material Usage Update code
//    public static boolean updateQty(ArrayList<> cartDetails) throws SQLException, ClassNotFoundException {
//        for (CartDetail cartDetail : cartDetails) {
//            if (!updateQty(new Item(cartDetail.getCode(), cartDetail.getDescription(), cartDetail.getUnitPrice(), cartDetail.getQty()))) {
//                return false;
//            }
//        }
//        return true;
//    }

//    private static boolean updateQty(Product product) throws SQLException, ClassNotFoundException {
//        String sql = "UPDATE product SET Quantity= ? WHERE prId = ?";
//        return Crudutil.execute(sql, product.getQuantity(),product.getPrid());
//    }

}
