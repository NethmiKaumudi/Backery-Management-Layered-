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
import lk.ijse.BackeryManagement.dao.MaterialStockDAOimpl;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.dto.MaterialStockDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import lk.ijse.BackeryManagement.view.tm.MaterialStockTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialStockFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtmId;

    @FXML
    private JFXTextField txtMaterialType;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private TableView<MaterialStockTm> tblMaterialStock;

    @FXML
    private TableColumn<?, ?> colMid;

    @FXML
    private TableColumn<?, ?> colMaterialType;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUid;
    @FXML
    private JFXTextField txtSearch;
    private String searchText = "";

    public void initialize(){
        colMid.setCellValueFactory(new PropertyValueFactory<>("Mid"));
        colMaterialType.setCellValueFactory(new PropertyValueFactory<>("MaterialType"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colUid.setCellValueFactory(new PropertyValueFactory<>("Uid"));

        tableView(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            tableView(searchText);
        });

    }
    private void tableView(String text){
        String searchText = "%" + text + "%";
        try {
            ObservableList<MaterialStockTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
//            String sql = "SELECT * From material_stock";
            String sql = "SELECT * From material_stock  WHERE  mId LIKE ?|| material_type LIKE ?||quantity LIKE ?|| uId LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            statement.setString(3,searchText);
            statement.setString(4,searchText);

            ResultSet set = statement.executeQuery();

            while (set.next()){
                MaterialStockTm materialStockTm=new MaterialStockTm(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)
                );
                tmList.add(materialStockTm);
            }

            tblMaterialStock.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e)  {
            System.out.println(e);
        }
    }


    @FXML
    void addbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Mid=txtmId.getText();
        String MaterialType=txtMaterialType.getText();
        String Quantity=txtQuantity.getText();
        String Uid=txtUserId.getText();

    MaterialStockDTO materialStock=new MaterialStockDTO(Mid,MaterialType,Quantity,Uid);
    boolean isAdded = MaterialStockDAOimpl.addMaterialStock(materialStock);
        tableView(searchText);
        if (isAdded) {
        clearFields();
        new Alert(Alert.AlertType.CONFIRMATION, "Material  Added!").show();
    } else {
        new Alert(Alert.AlertType.WARNING, "Something happened!").show();
    }


}
    private void clearFields(){
        txtmId.clear();
        txtMaterialType.clear();
        txtQuantity.clear();
        txtUserId.clear();
    }




    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM,pane);
    }

    @FXML
    void deletebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Mid = txtmId.getText();
        MaterialStockDTO materialStock=new MaterialStockDTO();
        materialStock.setMid(Mid);
        boolean isDeleted = MaterialStockDAOimpl.deleteMaterialStock(Mid);
        tableView(searchText);
        if (isDeleted) {
            // System.out.println("Deleted");
            new Alert(Alert.AlertType.CONFIRMATION, "Material Deleted!").show();
        }else{
            //System.out.println("Somthing Happend");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }


    @FXML
    void updatebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
       String Id =txtmId.getText();
       String MaterialType=txtMaterialType.getText();
       String Quantity=txtQuantity.getText();
       String Uid=txtUserId.getText();
       MaterialStockDTO materialStock=new MaterialStockDTO(Id,MaterialType,Quantity,Uid);
       boolean isUpdate = MaterialStockDAOimpl.updateMaterial(materialStock);


        if (isUpdate) {
            // System.out.println("Updated");
            new Alert(Alert.AlertType.CONFIRMATION, "Material Details Updated!").show();

        }else{
            // System.out.println("Not");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }
        tableView(searchText);
        clearFields();
    }


    @FXML
    void txtMidOnAction(ActionEvent event) {

        String Id = txtmId.getText();
        try {
            MaterialStockDTO materialStock=MaterialStockDAOimpl.searchMaterial(Id);
            if (materialStock!= null) {
                fillData(materialStock);
                // System.out.println( "Fill");
                new Alert(Alert.AlertType.CONFIRMATION, "MaterialStock Detailes Searched and filled  Fields succesfully!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void fillData(MaterialStockDTO materialStock) {
        txtmId.setText(materialStock.getMid());
        txtMaterialType.setText(materialStock.getMaterialType());
        txtQuantity.setText(materialStock.getQuantity());
        txtUserId.setText(materialStock.getUid());

    }


}
