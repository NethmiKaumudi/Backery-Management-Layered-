package lk.ijse.BackeryManagement.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Paint;
import lk.ijse.BackeryManagement.dao.SignUpDAOimpl;
import lk.ijse.BackeryManagement.dto.UserDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupFormController {
    @FXML
    private AnchorPane pane2;

    @FXML
    private JFXTextField txtusename;

    @FXML
    private JFXTextField txtpassword;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXTextField txtnic;
    @FXML
    private JFXTextField txtUid;

    @FXML
    private Label lbluid;

    @FXML
    private Label lblUserName;
    @FXML
    private Label lblPassWord;

    @FXML
    private Label lblmail;

    @FXML
    private Label lblNic;
    private Matcher uIdMatcher;
    private Matcher userNameMatcher;
    private Matcher pwMatcher;
    private Matcher emailMatcher;
    private Matcher nIcMatcher;
    private void setPatterns() {
        Pattern uIdPattern = Pattern.compile("^[U0-9]{4}$");
        uIdMatcher = uIdPattern.matcher(txtUid.getText());

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtusename.getText());

        Pattern pwPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        pwMatcher = pwPattern.matcher(txtpassword.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtemail.getText());

        Pattern nIcPattern=Pattern.compile("^(?:19|20)?\\d{2}(?:[0-35-8]\\d\\d(?<!(?:000|500|36[7-9]|3[7-9]\\d|86[7-9]|8[7-9]\\d)))\\d{4}(?i:v|x)$");
        nIcMatcher=nIcPattern.matcher(txtnic.getText());
    }
    public  void initialize(){
     setPatterns();
    }
    @FXML
    void signupbtnOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        if (uIdMatcher.matches()) {
            if (userNameMatcher.matches()) {
                if (pwMatcher.matches()) {
                    if (emailMatcher.matches()) {
                        if (nIcMatcher.matches()) {

                        }
                    }
                }

            }

        }
        String Uid=txtUid.getText();
        String UserName=txtusename.getText();
        String PassWord=txtpassword.getText();
        String Email=txtemail.getText();
        String Nic=txtnic.getText();

        UserDTO user =new UserDTO(Uid,UserName,PassWord,Email,Nic);
        boolean isAdded = SignUpDAOimpl.signinUser(user);

        if (isAdded) {
          //  clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Sign in Successfully!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

        Navigation.navigate(Routes.DASHBOARDFORM, pane2);
    }


@FXML
void txtuIdKeyTypedOnAction(KeyEvent event) {
        lbluid.setText("");
        txtUid.setFocusColor(Paint.valueOf("Blue"));

        Pattern uIdPattern = Pattern.compile("^([U0-9]{4})$");
        uIdMatcher = uIdPattern.matcher(txtUid.getText());


        if(!uIdMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtUid.requestFocus();
            txtUid.setFocusColor(Paint.valueOf("Red"));
            lbluid.setText("invalid userId");
      }

}

    @FXML
    void txtEmailKeyTypedOnAction(KeyEvent event) {
        lblmail.setText("");
        txtemail.setFocusColor(Paint.valueOf("Blue"));


        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtemail.getText());


        if(!emailMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtemail.requestFocus();
            txtemail.setFocusColor(Paint.valueOf("Red"));
            lblmail.setText("invalid Email");
        }

    }

    @FXML
    void txtNicKeyTypedOnAction(KeyEvent event) {
        lblNic.setText("");
        txtnic.setFocusColor(Paint.valueOf("Blue"));

        Pattern nIcPattern=Pattern.compile("^(?:19|20)?\\d{2}(?:[0-35-8]\\d\\d(?<!(?:000|500|36[7-9]|3[7-9]\\d|86[7-9]|8[7-9]\\d)))\\d{4}(?i:v|x)$");
        nIcMatcher=nIcPattern.matcher(txtnic.getText());

        if(!nIcMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtnic.requestFocus();
            txtnic.setFocusColor(Paint.valueOf("Red"));
            lblNic.setText("invalid Nic");
        }

    }

    @FXML
    void txtPassWordKeyTypedOnAction(KeyEvent event) {
        lblPassWord.setText("");
        txtpassword.setFocusColor(Paint.valueOf("Blue"));

        Pattern pwPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        pwMatcher = pwPattern.matcher(txtpassword.getText());

        if(!pwMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtpassword.requestFocus();
            txtpassword.setFocusColor(Paint.valueOf("Red"));
            lblPassWord.setText("invalid PassWord");
        }

    }

    @FXML
    void txtUserNameKeyTypedOnAction(KeyEvent event) {
        lblUserName.setText("");
        txtusename.setFocusColor(Paint.valueOf("Blue"));

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtusename.getText());
        if(!userNameMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtusename.requestFocus();
            txtusename.setFocusColor(Paint.valueOf("Red"));
            lblUserName.setText("invalid user name");
        }

    }

}






