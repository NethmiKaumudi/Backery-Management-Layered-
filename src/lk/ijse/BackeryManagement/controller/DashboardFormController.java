package lk.ijse.BackeryManagement.controller;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.scenario.effect.ZoomRadialBlur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import org.jfree.chart.plot.Zoomable;

import java.io.IOException;
import java.time.LocalDate;

public class DashboardFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblDate;

    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3;

    @FXML
    private JFXButton Productbtn;
    @FXML
    private Button btnLogOut;

    @FXML
    private JFXButton btnEmployee;
    @FXML
    private JFXButton btnVehicle;

    @FXML
    private JFXButton btnMStock;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnAttendance;

    @FXML
    private JFXButton btnpayroll;

    @FXML
    private JFXButton btnMateralusage;

    @FXML
    private JFXButton btnDeliveryDetails;
    @FXML
    private JFXButton btnReport;





    public  void initialize() throws IOException {
    setDate();

}

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }


    @FXML
    void ProuctbtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(Productbtn).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.PRODUCTFORM,pane);
    }

    @FXML
    void emplyeebtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnEmployee).play();
        new ZoomIn(pane).play();
      Navigation.navigate(Routes.EMPLOYEEFORM,pane);
    }
    @FXML
    void AttendancebtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnAttendance).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.ATTENDANCEFORM,pane);
    }

    @FXML
    void DeliverydetailsbtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnDeliveryDetails).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.DELIVERYDETAILSFORM,pane);
    }


    @FXML
    void MaterialStockbtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnMStock).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.MATERIALSTOCKFORM,pane);
    }

    @FXML
    void MaterialUsagebtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnMateralusage).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.MATERIALUSAGEFORM,pane);
    }

    @FXML
    void paymentbtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnPayment).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.PAYMENTFORM,pane);
    }

    @FXML
    void payrollbtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnpayroll).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.PAYROLLFORM,pane);
    }

    @FXML
    void reportbtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnReport).play();
        new ZoomIn(pane).play();
        Navigation.navigate(Routes.REPORTSFORM,pane);
    }

    @FXML
    void vehiclebtnOnAction(ActionEvent event) throws IOException {
        new ZoomInDown(btnVehicle).play();
        new ZoomIn(pane).play();
    Navigation.navigate(Routes.VEHICLEFORM,pane);
    }
    @FXML
    void LogOutbtnOnAction(ActionEvent event) {
    System.exit(0);
    }



}
