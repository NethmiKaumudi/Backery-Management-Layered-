package lk.ijse.BackeryManagement.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BackeryManagement.dao.ProductDAOimpl;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.dto.ProductDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import lk.ijse.BackeryManagement.view.tm.ProductTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtProductId;

    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXTextField txtUnitPrice;
    @FXML

    private JFXTextField txtProductQuantity;

    @FXML
    private TableView<ProductTm> tblProduct;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> ColProductName;


    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colProductQuantity;
    @FXML
    private JFXTextField txtSearch;
    private String searchText = "";

    public void initialize(){
        colProductId.setCellValueFactory(new PropertyValueFactory<>("Prid"));
        ColProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colProductQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        tableView(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            tableView(searchText);
        });

    }
    private void tableView(String text){
        String searchText = "%" + text + "%";
        try {
            ObservableList<ProductTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
           // String sql = "SELECT * From product";
                String sql = "SELECT * From product  WHERE  prId LIKE ?||product_name LIKE ?||unit_price LIKE ?|| Quantity LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            statement.setString(3,searchText);
            statement.setString(4,searchText);

            ResultSet set = statement.executeQuery();

            while (set.next()){
                ProductTm productTm=new ProductTm(
                        set.getString(1),
                        set.getString(2),
                        set.getDouble(3),
                        set.getInt(4)
                );
                tmList.add(productTm);
            }

            tblProduct.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e)  {
            System.out.println(e);
        }
    }

    @FXML
    void addbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String prId=txtProductId.getText();
        String productName=txtProductName.getText();
        Double unitPrice= Double.valueOf(txtUnitPrice.getText());
        int qty= Integer.parseInt(txtProductQuantity.getText());
        ProductDTO product=new ProductDTO(prId,productName,unitPrice,qty);
        boolean isAdded = ProductDAOimpl.addProduct(product);
        tableView(searchText);
        if (isAdded) {
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Product Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }



    }
    private void clearFields() {
        txtProductId.clear();
        txtProductName.clear();
        txtUnitPrice.clear();
        txtProductQuantity.clear();
    }

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM,pane);
    }

    @FXML
    void deletebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String prId = txtProductId.getText();
        ProductDTO product=new ProductDTO();

        product.setPrid(prId);
        boolean isDeleted = ProductDAOimpl.deleteProduct(product);
        tableView(searchText);
        if (isDeleted) {
            // System.out.println("Deleted");
            new Alert(Alert.AlertType.CONFIRMATION, "Product Deleted!").show();
        }else{
            //System.out.println("Somthing Happend");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }
//
//    @FXML
//    void loadbtnOnAction(ActionEvent event) {
//     tableView();
//    }

    @FXML
    void updatebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String prId =txtProductId.getText();
        String productName=txtProductName.getText();
        Double UnitPrice= Double.valueOf(String.valueOf(txtUnitPrice.getText()));
        int qty= Integer.parseInt(String.valueOf(txtProductQuantity.getText()));



        ProductDTO product=new ProductDTO(prId,productName, UnitPrice,qty);
        boolean isUpdate = ProductDAOimpl.updateProduct(product);


        if (isUpdate) {
            // System.out.println("Updated");
            new Alert(Alert.AlertType.CONFIRMATION, "Product Details Updated!").show();

        }else{
            // System.out.println("Not");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }
        tableView(searchText);
        clearFields();

    }
    @FXML
    void txtprIdOnAction(ActionEvent event) {
        String prId = txtProductId.getText();
        try {
            ProductDTO product= ProductDAOimpl.search(prId);
            if (product != null) {
                fillData(product);
                // System.out.println( "Fill");
                new Alert(Alert.AlertType.CONFIRMATION, "Product  Detailes Searched and filled  Fields succesfully!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void fillData(ProductDTO product) {
        txtProductId.setText(product.getPrid());
        txtProductName.setText(product.getProductName());
        txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        txtProductQuantity.setText(String.valueOf(product.getQuantity()));
    }

}


//[PR0-9]{4}
