package lk.ijse.BackeryManagement.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.model.PaymentModel;
import lk.ijse.BackeryManagement.dto.PaymentDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import lk.ijse.BackeryManagement.view.tm.PaymentTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PaymentFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXTextField txtpId;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtmId;

    @FXML
    private Label lblDate;
    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPid;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colMID;

    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXTextField txtDate;
    private String searchText = "";

    public  void initialize(){
    setDate();

        colPid.setCellValueFactory(new PropertyValueFactory("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));
        colDesc.setCellValueFactory(new PropertyValueFactory("Description"));
        colPrice.setCellValueFactory(new PropertyValueFactory("Price"));
        colMID.setCellValueFactory(new PropertyValueFactory("Id"));

        tableView(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            tableView(searchText);
        });
    }

    private void tableView(String text){
        String searchText = "%" + text + "%";
        try {
            ObservableList<PaymentTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            //  String sql = "SELECT * From employee";
            String sql = "SELECT * From payment  WHERE  pId LIKE ?||date LIKE ?||description LIKE ?||price LIKE ?||mId LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            statement.setString(3,searchText);
            statement.setString(4,searchText);
            statement.setString(5,searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()){
                PaymentTm paymentTm=new PaymentTm(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getDouble(4),
                        set.getString(5)
                );
                tmList.add(paymentTm);
            }

            tblPayment.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e)  {
            System.out.println(e);
        }
    }

    @FXML
    void addbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

       String paymentId=txtpId.getText();
       String Date=txtDate.getText();
       String Description=txtDesc.getText();
       Double Price= Double.valueOf(txtPrice.getText());
       String Id=txtmId.getText();

       PaymentDTO payment=new PaymentDTO(paymentId,Date,Description,Price,Id);
        boolean isAdded = PaymentModel.addPayment(payment);
        tableView(searchText);
        if (isAdded) {
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Payment  Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }


    }
    private void clearFields(){
        txtpId.clear();
        txtDesc.clear();
        txtPrice.clear();
        txtmId.clear();
    }


    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM,pane);
    }

    @FXML
    void deletebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String paymentId = txtpId.getText();
        PaymentDTO payment=new PaymentDTO();
        payment.setPaymentId(paymentId);
        boolean isDeleted = PaymentModel.deletePayment(payment);
       tableView(searchText);
        if (isDeleted) {
            // System.out.println("Deleted");
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Details Deleted!").show();
        }else{
            //System.out.println("Somthing Happend");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
        clearFields();
    }

    @FXML
    void txtpIdOnAction(ActionEvent event) {


        String paymentId = txtpId.getText();
        try {
            PaymentDTO payment=PaymentModel.searchPayment(paymentId);
            if (payment != null) {
                fillData(payment);
                // System.out.println( "Fill");
                new Alert(Alert.AlertType.CONFIRMATION, "Payment  Detailes Searched and filled  Fields succesfully!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //tableView();

    }

    private void fillData(PaymentDTO payment) {
        txtpId.setText(payment.getPaymentId());
        txtDate.setText(payment.getDate());
        txtDesc.setText(payment.getDescription());
        txtPrice.setText(String.valueOf(payment.getPrice()));
        txtmId.setText(payment.getId());
    }

    @FXML
    void updatebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String nic =txtnic.getText();
//        String name=txtname.getText();
//        String address=txtaddress.getText();
//        String licenceNo=txtlicenceno.getText();
//        int contact= Integer.parseInt(String.valueOf(txtcontact.getText()));
//        String position=txtPosition.getText();
//        Double BasicSalary= Double.valueOf(txtSalary.getText());
        String paymentId=txtpId.getText();
        String Date=txtDate.getText();
        String Description=txtDesc.getText();
        Double Price= Double.valueOf(txtPrice.getText());
        String Id=txtmId.getText();

        PaymentDTO payment=new PaymentDTO(paymentId,Date,Description,Price,Id);
        boolean isUpdate = PaymentModel.updatePayment(payment);


        if (isUpdate) {
            // System.out.println("Updated");
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Details Updated!").show();

        }else{
            // System.out.println("Not");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }
        tableView(searchText);
        clearFields();
    }

    private void setDate() {
        txtDate.setText(String.valueOf(LocalDate.now()));
    }


    }



