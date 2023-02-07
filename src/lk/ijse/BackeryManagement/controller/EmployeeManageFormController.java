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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.BackeryManagement.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.BackeryManagement.dao.custom.EmployeeDAO;
import lk.ijse.BackeryManagement.dto.EmployeeDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeManageFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<EmployeeDTO> tblemployee;

    @FXML
    private TableColumn colNic;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> collicenceNo;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private JFXTextField txtnic;

    @FXML
    private JFXTextField txtname;

    @FXML
    private JFXTextField txtlicenceno;

    @FXML
    private JFXTextField txtcontact;

    @FXML
    private JFXTextField txtPosition;

    @FXML
    private JFXTextField txtaddress;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private Label lblNic;
    @FXML
    private Label lblName;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblVlicenceNo;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblSalary;

    private String searchText = "";
    private Matcher NicMatcher;
    private Matcher NameMatcher;
    private Matcher AddressMatcher;
    private Matcher licenceNoMatcher;
    private Matcher ContactMatcher;
    private Matcher PositionMatcher;
    private Matcher SalaryMatcher;

    EmployeeDTO employee=new EmployeeDTO();
    EmployeeDAO employeeDAOimpl = new EmployeeDAOImpl();

    public void initialize(){

       setPatterns();
        colNic.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collicenceNo.setCellValueFactory(new PropertyValueFactory<>("licenceNo"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("BasicSalary"));


        tableView(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            tableView(searchText);
        });


    }
    private void tableView(String text) {
        ObservableList<EmployeeDTO> tmList = FXCollections.observableArrayList();
        String searchText = "%" + text + "%";
        ArrayList<EmployeeDTO> employeeDTOS = null;
        try {
            employeeDTOS = employeeDAOimpl.tableView();
            for(EmployeeDTO s : employeeDTOS){
                tmList.add(s);
                tblemployee.setItems(tmList);
                initialize();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
      //  for(EmployeeDTO s : employeeDTOS){



//
//            Connection connection = DBConnection.getInstance().getConnection();
//       //  String sql = "SELECT * From employee";
//            String sql = "SELECT * From employee  WHERE  nIc LIKE ?||name LIKE ?||address LIKE ?||vehicle_licance_number LIKE ?||contact LIKE ?||position LIKE ?||basic_salary LIKE ?";
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1,searchText);
//            statement.setString(2,searchText);
//            statement.setString(3,searchText);
//            statement.setString(4,searchText);
//            statement.setString(5,searchText);
//            statement.setString(6,searchText);
//            statement.setString(7,searchText);
//            ResultSet set = statement.executeQuery();
//
//            while (set.next()){
//               EmployeeTM employeeTM=new EmployeeTM(
//                       set.getString(1),
//                       set.getString(2),
//                       set.getString(3),
//                       set.getString(4),
//                       set.getInt(5),
//                       set.getString(6),
//                       set.getDouble(7)
//                       );
               // tmList.add(employeeTM);
            //employeeDAOimpl.
          //  }




    }
 private void setPatterns(){
     Pattern nIcPattern=Pattern.compile("^(?:19|20)?\\d{2}(?:[0-35-8]\\d\\d(?<!(?:000|500|36[7-9]|3[7-9]\\d|86[7-9]|8[7-9]\\d)))\\d{4}(?i:v|x)$");
     NicMatcher=nIcPattern.matcher(txtnic.getText());
     Pattern namePattern=Pattern.compile("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$");
     NameMatcher=namePattern.matcher(txtname.getText());
     Pattern addressPattern=Pattern.compile("^[a-zA-Z]{1,10}$");
     AddressMatcher=addressPattern.matcher(txtaddress.getText());
     Pattern licenceNoPattern=Pattern.compile("^[0-9]{1,7}$");
     licenceNoMatcher=licenceNoPattern.matcher(txtlicenceno.getText());
     Pattern contactPattern=Pattern.compile("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$");
     ContactMatcher=contactPattern.matcher(txtcontact.getText());
     Pattern positionPattern=Pattern.compile("^[A-Z][a-z]+[A-Za-z]*$");
     PositionMatcher= positionPattern.matcher(txtPosition.getText());
     Pattern salaryPattern=Pattern.compile("^[0-9][1-9.]*[0-9]+[1-9]*$");
     SalaryMatcher= salaryPattern.matcher(txtSalary.getText());


 }
    @FXML
    void addbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(NicMatcher.matches()){
            if(NameMatcher.matches()){
                if(AddressMatcher.matches()){
                    if(licenceNoMatcher.matches()){
                        if(ContactMatcher.matches()){
                            if(PositionMatcher.matches()){
                                if(SalaryMatcher.matches()){

                                }
                            }
                        }
                    }
                }

            }

        }
        String Nic=txtnic.getText();
        String name=txtname.getText();
        String address=txtaddress.getText();
        String licenceNo=txtlicenceno.getText();
        int contact= Integer.parseInt(txtcontact.getText());
        String position=txtPosition.getText();
        Double BasicSalary= Double.valueOf(txtSalary.getText());
      //  EmployeeDTO employee=new EmployeeDTO(Nic,name,address,licenceNo,contact,position,BasicSalary);
        boolean isAdded = employeeDAOimpl.add(new EmployeeDTO(Nic,name,address,licenceNo,contact,position,BasicSalary));
        tableView(searchText);
        if (isAdded) {
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }


    }
    private void clearFields(){
        txtnic.clear();
        txtname.clear();
        txtaddress.clear();
        txtlicenceno.clear();
        txtcontact.clear();
        txtPosition.clear();
        txtSalary.clear();
    }
    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
    Navigation.navigate(Routes.SUMMARYFORM,pane);
    }

    @FXML
    void deletebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String nic = txtnic.getText();
        try {

            employee.setNic(nic);
            boolean delete = employeeDAOimpl.delete(nic);
            tableView(searchText);
            if (delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                initialize();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void nicOnAction(ActionEvent event) {
        String Nic = txtnic.getText();
        try {
            employee = employeeDAOimpl.search(Nic);
            if (employee != null) {
                fillData(employee);
               // System.out.println( "Fill");
                new Alert(Alert.AlertType.CONFIRMATION, "Employee  Detailes Searched and filled  Fields succesfully!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       //tableView();

    }

    private void fillData(EmployeeDTO employee) {
        txtnic.setText(employee.getNic());
        txtname.setText(employee.getName());
        txtaddress.setText(employee.getAddress());
        txtlicenceno.setText(String.valueOf(employee.getLicenceNo()));
        txtcontact.setText(String.valueOf(employee.getContact()));
        txtPosition.setText(employee.getPosition());
        txtSalary.setText(String.valueOf(employee.getBasicSalary()));
    }

    @FXML
    void updatebtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String nic =txtnic.getText();
        String name=txtname.getText();
        String address=txtaddress.getText();
        String licenceNo=txtlicenceno.getText();
        int contact= Integer.parseInt(String.valueOf(txtcontact.getText()));
        String position=txtPosition.getText();
        Double BasicSalary= Double.valueOf(txtSalary.getText());


//       EmployeeDTO employee=new EmployeeDTO(nic,name,address,licenceNo,contact,position,BasicSalary);
        boolean isUpdate = employeeDAOimpl. update(new EmployeeDTO(nic,name,address,licenceNo,contact,position,BasicSalary));


        if (isUpdate) {
           // System.out.println("Updated");
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Details Updated!").show();

        }else{
           // System.out.println("Not");
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();

        }
        tableView(searchText);
        clearFields();
    }
    @FXML
    void txtaddressKeyTypedOnAction(KeyEvent event) {
        lblAddress.setText("");
        txtaddress.setFocusColor(Paint.valueOf("Blue"));
        Pattern addressPattern=Pattern.compile("^[a-zA-Z]{1,10}$");
        AddressMatcher=addressPattern.matcher(txtaddress.getText());


        if(!AddressMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtaddress.requestFocus();
            txtaddress.setFocusColor(Paint.valueOf("Red"));
            lblAddress.setText("invalid Name");
        }
    }

    @FXML
    void txtbasicSalaryKeyTypedOnAction(KeyEvent event) {
        lblSalary.setText("");
        txtSalary.setFocusColor(Paint.valueOf("Blue"));

        Pattern salaryPattern=Pattern.compile("^[0-9][1-9.]*[0-9]+[1-9]*$");
        SalaryMatcher= salaryPattern.matcher(txtSalary.getText());

        if(!SalaryMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtSalary.requestFocus();
            txtSalary.setFocusColor(Paint.valueOf("Red"));
            lblSalary.setText("invalid Salary");
        }
    }

    @FXML
    void txtcontactKeyTypedOnAction(KeyEvent event) {
        lblContact.setText("");
        txtcontact.setFocusColor(Paint.valueOf("Blue"));

        Pattern contactPattern=Pattern.compile("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$");
        ContactMatcher=contactPattern.matcher(txtcontact.getText());

        if(!ContactMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtcontact.requestFocus();
            txtcontact.setFocusColor(Paint.valueOf("Red"));
            lblContact.setText("invalid Contact");
        }
    }

    @FXML
    void txtlicenceNoKeyTypedOnAction(KeyEvent event) {
        lblVlicenceNo.setText("");
        txtlicenceno.setFocusColor(Paint.valueOf("Blue"));

        Pattern licenceNoPattern=Pattern.compile("^[0-9]{1,7}$");
        licenceNoMatcher=licenceNoPattern.matcher(txtlicenceno.getText());

        if(!licenceNoMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtlicenceno.requestFocus();
            txtlicenceno.setFocusColor(Paint.valueOf("Red"));
            lblVlicenceNo.setText("invalid Licence No");
        }
    }

    @FXML
    void txtnameKeyTypedOnAction(KeyEvent event) {
        lblName.setText("");
        txtname.setFocusColor(Paint.valueOf("Blue"));
        Pattern namePattern=Pattern.compile("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$");
        NameMatcher=namePattern.matcher(txtname.getText());


        if(!NameMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtname.requestFocus();
            txtname.setFocusColor(Paint.valueOf("Red"));
            lblName.setText("invalid Name");
        }

    }

    @FXML
    void txtnicKeyTypedOnAction(KeyEvent event) {

        lblNic.setText("");
        txtnic.setFocusColor(Paint.valueOf("Blue"));

        Pattern nIcPattern=Pattern.compile("^(?:19|20)?\\d{2}(?:[0-35-8]\\d\\d(?<!(?:000|500|36[7-9]|3[7-9]\\d|86[7-9]|8[7-9]\\d)))\\d{4}(?i:v|x)$");
        NicMatcher=nIcPattern.matcher(txtnic.getText());

        if(!NicMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtnic.requestFocus();
            txtnic.setFocusColor(Paint.valueOf("Red"));
           lblNic.setText("invalid Nic");
        }
        }


    @FXML
    void txtpositionKeyTypedOnAction(KeyEvent event) {
        lblPosition.setText("");
        txtPosition.setFocusColor(Paint.valueOf("Blue"));
        Pattern positionPattern=Pattern.compile("^[A-Z][a-z]+[A-Za-z]*$");
        PositionMatcher= positionPattern.matcher(txtPosition.getText());

        if(!PositionMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtPosition.requestFocus();
            txtPosition.setFocusColor(Paint.valueOf("Red"));
            lblPosition.setText("invalid position name");
        }
    }

}




