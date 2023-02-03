package lk.ijse.BackeryManagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BackeryManagement.db.DBConnection;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class ReortsFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    void attendancebtnOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/BackeryManagement/report/Attendance.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM,pane);
    }

    @FXML
    void deliverybtnOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/BackeryManagement/report/DeliveryDetails.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);



    }

    @FXML
    void paymentreporbtnOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/BackeryManagement/report/MaterialPayment.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);


    }

    @FXML
    void paysheetbtnOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/BackeryManagement/report/Paysheetnew.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

}


