package lk.ijse.BackeryManagement.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BackeryManagement.dao.custom.impl.ProductDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.impl.VehicleDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.ProductDAO;
import lk.ijse.BackeryManagement.dao.custom.VehicleDAO;
import lk.ijse.BackeryManagement.model.DeliveryVehicleModel;
import lk.ijse.BackeryManagement.dto.DeliveryDetailsTableDTO;
import lk.ijse.BackeryManagement.dto.DeliveryVehicleDTO;
import lk.ijse.BackeryManagement.dto.ProductDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import lk.ijse.BackeryManagement.view.tm.DeliveryDetailsTabletm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class DeliveryDetailsFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtProductName;

    @FXML
    private JFXComboBox<String> cmbvNo;

    @FXML
    private JFXComboBox<String> cmbprId;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private TableView<DeliveryDetailsTabletm> tbldeliveryDetails;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colvNo;

    @FXML
    private TableColumn colprId;

    @FXML
    private TableColumn colproductName;

    @FXML
    private TableColumn colunitPrice;

    @FXML
    private TableColumn colQuantity;

    @FXML
    private TableColumn colOption;

    @FXML
    private JFXTextField txtProductQty;

    @FXML
    private JFXTextField txtDate;
    ProductDAO productDAOimpl = new ProductDAOimpl();
    VehicleDAO vehicleDAOimpl = new VehicleDAOimpl();
    private ObservableList<DeliveryDetailsTabletm> obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        setDate();
        loadVehicleNos();
        loadProductIds();
        setCellValueFactory();
    }

    private void loadProductIds() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = productDAOimpl.loadProductIds();

        for (String id : idList) {
            observableList.add(id);
        }
        cmbprId.setItems(observableList);
    }

    private void loadVehicleNos() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = vehicleDAOimpl.loadVehicleNos();

        for (String No : idList) {
            observableList.add(No);
        }
        cmbvNo.setItems(observableList);
    }


    private void setDate() {
        txtDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void addbtnOnAction(ActionEvent event) {
        String date = txtDate.getText();
        String vehicleNo = cmbvNo.getValue();
        String prId = cmbprId.getValue();
        String productName = txtProductName.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        int productQty = Integer.parseInt(txtProductQty.getText());
        Button btnDelete = new Button("Delete");
        txtProductQty.setText("");

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tbldeliveryDetails.getItems().size(); i++) {
                if (colprId.getCellData(i).equals(prId)) {
                    productQty += (int) colQuantity.getCellData(i);

                    obList.get(i).setProductQty(productQty);
                    tbldeliveryDetails.refresh();
                    return;
                }
            }
        }
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.OK)) {
                System.out.println("ok");
                DeliveryDetailsTabletm tm = tbldeliveryDetails.getSelectionModel().getSelectedItem();

                tbldeliveryDetails.getItems().remove(tm);
            }
        });
        obList.add(new DeliveryDetailsTabletm(date, vehicleNo, prId, productName, unitPrice, productQty, btnDelete));
        tbldeliveryDetails.setItems(obList);

    }

    private void setCellValueFactory() {
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colvNo.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
        colprId.setCellValueFactory(new PropertyValueFactory("prId"));
        colproductName.setCellValueFactory(new PropertyValueFactory("productName"));
        colunitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory("productQty"));
        colOption.setCellValueFactory(new PropertyValueFactory("btnDelete"));


    }

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM, pane);
    }

    @FXML
    void deliverybtnOnAction(ActionEvent event) {
        String Date = txtDate.getText();

        String vehicleNo = String.valueOf(cmbvNo.getValue());

        ArrayList<DeliveryDetailsTableDTO> deliveryDetailTables = new ArrayList<>();


        for (int i = 0; i < tbldeliveryDetails.getItems().size(); i++) {
            DeliveryDetailsTabletm tm = obList.get(i);
            deliveryDetailTables.add(new DeliveryDetailsTableDTO(Date, tm.getVehicleNo(), tm.getPrId(), tm.getProductName(), tm.getUnitPrice(), tm.getProductQty()));
        }

        DeliveryVehicleDTO deliveryVehicle = new DeliveryVehicleDTO(Date, vehicleNo, deliveryDetailTables);
        DeliveryVehicleModel deliveryVehicleModel = new DeliveryVehicleModel();
        try {
            boolean isDelivered = deliveryVehicleModel.deliveryVehicles(deliveryVehicle);
            if (isDelivered) {
                /* to clear table */
                obList.clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Delivered!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happend!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbprIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String id = String.valueOf(cmbprId.getValue());
        ProductDTO product = productDAOimpl.search(id);
        txtProductName.setText(product.getProductName());
        txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        txtProductQty.requestFocus();
    }

    public void txtqtyOnAction(ActionEvent actionEvent) {
        addbtnOnAction(actionEvent);
    }

}


