package lk.ijse.BackeryManagement.util;

import animatefx.animation.Bounce;
import animatefx.animation.ZoomIn;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;
    public static void navigate(Routes routes,AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (routes) {
            case SIGNUPFORM:
                window.setTitle("SignUp Form");
                initUI("Signup Form.fxml");
                break;
            case DASHBOARDFORM:
                window.setTitle("Dashboard Form");
                initUI("Dashboard Form.fxml");
                break;
            case EMPLOYEEFORM:
                window.setTitle("Employee Form");
                initUI("Employee Manage Form.fxml");
                break;
            case PRODUCTFORM:
                window.setTitle("Product Form");
                initUI("Product Form.fxml");
                break;
            case VEHICLEFORM:
                window.setTitle("Vehicle Form");
                initUI("Vehicle Form.fxml");
                break;
            case MATERIALSTOCKFORM:
                window.setTitle("MaterialStock Form");
                initUI("MaterialStock Form.fxml");
                break;
            case PAYMENTFORM:
                window.setTitle("Payment Form");
                initUI("Payment Form.fxml");
                break;
            case PAYROLLFORM:
                window.setTitle("Payroll Form");
                initUI("PayRoll Form.fxml");
                break;
            case ATTENDANCEFORM:
                window.setTitle("Attendance Form");
                initUI("Attendance Form.fxml");
                break;
            case MATERIALUSAGEFORM:
                window.setTitle("MaterialUsage  Form");
                initUI("Material Usage Form.fxml");
                break;
            case DELIVERYDETAILSFORM:
                window.setTitle("DeliveryDetails Form");
                initUI("DeliveryDetails Form.fxml");
                break;
            case REPORTSFORM:
                window.setTitle("Reportsb Form");
                initUI("Reorts Form.fxml");
                break;
            case SUMMARYFORM:
                initUI("SummaryForm.fxml");
                break;


            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();

        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/BackeryManagement/view/" + location)));
    }


}
