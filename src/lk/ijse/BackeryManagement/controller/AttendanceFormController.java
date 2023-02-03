package lk.ijse.BackeryManagement.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.BackeryManagement.dao.EmployeeDAOimpl;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.model.AttendanceModel;
import lk.ijse.BackeryManagement.dto.AttendanceDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import lk.ijse.BackeryManagement.view.tm.AttendanceTm;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttendanceFormController {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<AttendanceTm> tblAttendance;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colNic;

    @FXML
    private TableColumn colAttendance;

    @FXML
    private JFXComboBox<String> cmbNic;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtAttendance;
    private String searchText = "";


    public void initialize() throws SQLException, ClassNotFoundException {
        setDate();
        loadNics();


        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colAttendance.setCellValueFactory(new PropertyValueFactory("attendance"));
        colNic.setCellValueFactory(new PropertyValueFactory("Nic"));
        tableView(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            tableView(searchText);
        });
    }

    private void tableView(String text) {
        String searchText = "%" + text + "%";
        try {
            ObservableList<AttendanceTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            //  String sql = "SELECT * From employee";
            String sql = "SELECT * From attendance  WHERE  date LIKE ?||attendance LIKE ?||nIc LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, searchText);
            statement.setString(2, searchText);
            statement.setString(3, searchText);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                AttendanceTm attendanceTm = new AttendanceTm(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3)
                );
                tmList.add(attendanceTm);
            }

            tblAttendance.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }


    private void loadNics() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = EmployeeDAOimpl.loadEmployeeNics();

        for (String nic : idList) {
            observableList.add(nic);
        }
        cmbNic.setItems(observableList);
    }

    private void setDate() {
        txtDate.setText(String.valueOf(LocalDate.now()));
    }


    @FXML
    void AddbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String date = txtDate.getText();
        String Attendance = txtAttendance.getText();
        String Nic = cmbNic.getValue();
        // AttendanceDTO attendance = new AttendanceDTO(date, Attendance, Nic);
        boolean isAdded = AttendanceModel.addAttendance(new AttendanceDTO(date, Attendance, Nic));
        tableView(searchText);
        if (isAdded) {
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Attendance Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
    }

    private void clearFields() {

        txtAttendance.clear();
    }

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM, pane);
    }

    @FXML
    void deletebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String date = txtDate.getText();
        String Nic = cmbNic.getValue();
        AttendanceDTO attendance = new AttendanceDTO();
        attendance.setDate(date);
        attendance.setNic(Nic);
        boolean isDeleted = AttendanceModel.deleteAttendance(attendance);
        tableView(searchText);
        if (isDeleted) {
            // System.out.println("Deleted");
            new Alert(Alert.AlertType.CONFIRMATION, " Employee Attendance Deleted!").show();
        } else {
            //System.out.println("Somthing Happend");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }


    @FXML
    void updatebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String date = txtDate.getText();
        String Attendance = txtAttendance.getText();
        String nic = cmbNic.getValue();


        AttendanceDTO attendance = new AttendanceDTO(date, Attendance, nic);
        boolean isUpdate = AttendanceModel.updateAttendance(attendance);


        if (isUpdate) {
            // System.out.println("Updated");
            new Alert(Alert.AlertType.CONFIRMATION, "Attendance Details Updated!").show();

        } else {
            // System.out.println("Not");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }
        tableView(searchText);
        clearFields();
    }


    @FXML
    void cmbNicOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//        String id= String.valueOf(cmbProductId.getValue());
//        Product product= ProductModel.search(id);
//        txtProductName.setText(product.getProductName());
//        txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
//        txtProductQty.requestFocus();
        String date = txtDate.getText();
        String nic = cmbNic.getValue();
        AttendanceDTO attendance = AttendanceModel.searchAttendance(date, nic);
        txtAttendance.setText(attendance.getAttendance());
    }


}
