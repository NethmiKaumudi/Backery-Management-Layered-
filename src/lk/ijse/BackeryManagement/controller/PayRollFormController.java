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
import lk.ijse.BackeryManagement.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.BackeryManagement.dao.custom.impl.PayRollDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.EmployeeDAO;
import lk.ijse.BackeryManagement.dao.custom.PayRollDAO;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.dto.EmployeeDTO;
import lk.ijse.BackeryManagement.dto.PayrollDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import lk.ijse.BackeryManagement.view.tm.PayrollTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PayRollFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXComboBox<String> cmbNic;

    @FXML
    private JFXTextField txtmonthyear;

    @FXML
    private JFXTextField txtBasicSalary;

    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<PayrollTm> tblPayroll;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colMonthYear;

    @FXML
    private TableColumn<?, ?> colBasicSalary;

    @FXML
    private TableColumn<?, ?> colEmployeeEPF;

    @FXML
    private TableColumn<?, ?> colMonthlySalary;

    @FXML
    private TableColumn<?, ?> colEmpoyerEPF;

    @FXML
    private TableColumn<?, ?> colEmployerETF;

    private String searchText = "";
    PayrollDTO payroll = new PayrollDTO();
    PayRollDAO payRollDAOimpl = new PayRollDAOimpl();
    EmployeeDAO employeeDAOimpl = new EmployeeDAOImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        loadNics();
        colNic.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        colMonthYear.setCellValueFactory(new PropertyValueFactory<>("monthYear"));
        colBasicSalary.setCellValueFactory(new PropertyValueFactory<>("basicSalary"));
        colEmployeeEPF.setCellValueFactory(new PropertyValueFactory<>("employeeEPF"));
        colMonthlySalary.setCellValueFactory(new PropertyValueFactory<>("monthlySalary"));
        colEmpoyerEPF.setCellValueFactory(new PropertyValueFactory<>("employerEPF"));
        colEmployerETF.setCellValueFactory(new PropertyValueFactory<>("employerETF"));


        tableView(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            tableView(searchText);
        });

    }


    private void tableView(String text) {
        String searchText = "%" + text + "%";
        try {
            ObservableList<PayrollTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            //  String sql = "SELECT * From employee";
            String sql = "SELECT * From payroll WHERE  nIC  LIKE ?||month_year LIKE ?||basic_salary LIKE ?|| employee_EPF LIKE ?||monthly_salary LIKE ?||employer_EPF LIKE ?||employer_ETF  LIKE ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, searchText);
            statement.setString(2, searchText);
            statement.setString(3, searchText);
            statement.setString(4, searchText);
            statement.setString(5, searchText);
            statement.setString(6, searchText);
            statement.setString(7, searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                PayrollTm payrollTm = new PayrollTm(
                        set.getString(1),
                        set.getString(2),
                        set.getDouble(3),
                        set.getDouble(4),
                        set.getDouble(5),
                        set.getDouble(6),
                        set.getDouble(7)

                );
                tmList.add(payrollTm);
            }

            tblPayroll.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    private void loadNics() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = employeeDAOimpl.load();

        for (String nic : idList) {
            observableList.add(nic);
        }
        cmbNic.setItems(observableList);
    }

    @FXML
    void addbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Nic = cmbNic.getValue();
        String monthYear = txtmonthyear.getText();
        Double basicSalary = Double.valueOf(txtBasicSalary.getText());
        Double employeeEPF = (basicSalary * 8) / 100;
        Double monthlySalary = basicSalary - employeeEPF;
        Double employerEPF = (basicSalary * 12) / 100;
        Double employerETF = (basicSalary * 3) / 100;


        boolean isAdded = payRollDAOimpl.add(new PayrollDTO(Nic, monthYear, basicSalary, employeeEPF, monthlySalary, employerEPF, employerETF));
        tableView(searchText);
        if (isAdded) {
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Payroll Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }

    private void clearFields() {
        txtmonthyear.clear();
    }

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM, pane);
    }

    @FXML
    void cmbnicOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Nic = String.valueOf(cmbNic.getValue());
        System.out.println(Nic);
        EmployeeDTO employee = employeeDAOimpl.search(Nic);
        txtBasicSalary.setText(String.valueOf(employee.getBasicSalary()));


    }


    @FXML
    void deletebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Nic = cmbNic.getValue();
        String monthYear = txtmonthyear.getText();
        payroll.setNic(Nic);
        payroll.setMonthYear(monthYear);
        boolean isDeleted = payRollDAOimpl.delete(payroll);
        tableView(searchText);
        if (isDeleted) {
            // System.out.println("Deleted");
            new Alert(Alert.AlertType.CONFIRMATION, " Employee Payroll Deleted!").show();
        } else {
            //System.out.println("Somthing Happend");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }


}
