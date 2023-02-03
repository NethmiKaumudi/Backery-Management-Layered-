package lk.ijse.BackeryManagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SummaryFormController {
    @FXML
    private AnchorPane pane;

    @FXML
    void LogOutbtnOnAction(ActionEvent event) {
    System.exit(0);
    }

}
