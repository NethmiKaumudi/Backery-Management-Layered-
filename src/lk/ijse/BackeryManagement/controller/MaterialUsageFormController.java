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
import lk.ijse.BackeryManagement.dao.custom.impl.MaterialStockDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.impl.ProductDAOimpl;
import lk.ijse.BackeryManagement.dao.custom.MaterialStockDAO;
import lk.ijse.BackeryManagement.dao.custom.ProductDAO;
import lk.ijse.BackeryManagement.model.UseMaterialModel;
import lk.ijse.BackeryManagement.dto.MaterialStockDTO;
import lk.ijse.BackeryManagement.dto.MaterialTableDTO;
import lk.ijse.BackeryManagement.dto.ProductDTO;
import lk.ijse.BackeryManagement.dto.UseMaterialDTO;
import lk.ijse.BackeryManagement.util.Navigation;
import lk.ijse.BackeryManagement.util.Routes;
import lk.ijse.BackeryManagement.view.tm.MaterialTableTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class MaterialUsageFormController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXComboBox<String> cmbProductId;

    @FXML
    private JFXComboBox<String> cmbMaterialId;

    @FXML
    private JFXTextField txtMaterialType;

    @FXML
    private JFXTextField txtProductQty;

    @FXML
    private JFXTextField txtMaterialQuantity;

    @FXML
    private TableView<MaterialTableTm> tblMaterialUsage;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colPrId;

    @FXML
    private TableColumn colProductName;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableColumn colProductQty;

    @FXML
    private TableColumn colMid;

    @FXML
    private TableColumn colMaterialType;

    @FXML
    private TableColumn colUid;

    @FXML
    private TableColumn colMaterialqty;

    @FXML
    private TableColumn colOption;

    @FXML
    private JFXTextField txtProductName;
    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtUserId;
    private ObservableList<MaterialTableTm> obList = FXCollections.observableArrayList();

    MaterialStockDAO materialStockDAOimpl = new MaterialStockDAOimpl();
    ProductDAO productDAOimpl = new ProductDAOimpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        setDate();
        loadProductId();
        loadMaterialId();
        setCellValueFactory();
    }

    private void loadMaterialId() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = materialStockDAOimpl.load();

        for (String mId : idList) {
            observableList.add(mId);
        }
        cmbMaterialId.setItems(observableList);
    }

    private void loadProductId() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = productDAOimpl.loadProductIds();

        for (String id : idList) {
            observableList.add(id);
        }
        cmbProductId.setItems(observableList);
    }

    private void setDate() {
        txtDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void backbtnOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUMMARYFORM, pane);
    }

    @FXML
    void cmbMaterialOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String mid = String.valueOf(cmbMaterialId.getValue());
        System.out.println(mid);
        MaterialStockDTO materialStock = materialStockDAOimpl.search(mid);
        txtMaterialType.setText(materialStock.getMaterialType());
        txtUserId.setText(materialStock.getUid());
        txtMaterialQuantity.requestFocus();
    }


    @FXML
    void cmbProductIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = String.valueOf(cmbProductId.getValue());
        ProductDTO product = productDAOimpl.search(id);
        txtProductName.setText(product.getProductName());
        txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        txtProductQty.requestFocus();
    }

    @FXML
    void useMaterialbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Date = txtDate.getText();

        ArrayList<MaterialTableDTO> materialUsage = new ArrayList();

        /* load all cart items' to materialusage arrayList */
        for (int i = 0; i < tblMaterialUsage.getItems().size(); i++) {
            /* get each row details to (materialtableTm)tm in each time and add them to the materialusage */
            MaterialTableTm tm = obList.get(i);
            materialUsage.add(new MaterialTableDTO(Date, tm.getPrId(), tm.getProductName(), tm.getUnitPrice(), tm.getPrductqty(), tm.getId(), tm.getMaterialtype(), tm.getMaterialqty(), tm.getUserId()));
        }

        UseMaterialDTO useMaterial = new UseMaterialDTO(Date, materialUsage);
        UseMaterialModel useMaterialModel = new UseMaterialModel();
        boolean isUsed = useMaterialModel.useMaterial(useMaterial);
        if (isUsed) {
            /* to clear table */
            obList.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Materiala used!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing happend!").show();
        }

    }

    @FXML
    void addbtnOnAction(ActionEvent event) {

        String date = txtDate.getText();
        String prId = String.valueOf(cmbProductId.getValue());
        String productName = txtProductName.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        int productqty = Integer.parseInt(txtProductQty.getText());
        String Id = String.valueOf(cmbMaterialId.getValue());
        String materialType = txtMaterialType.getText();
        String userId = txtUserId.getText();
        String materialqty = txtMaterialQuantity.getText();
        Button btnDelete = new Button("Delete");
        txtProductQty.setText("");
        txtMaterialQuantity.setText("");

        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblMaterialUsage.getItems().size(); i++) {
                if (colPrId.getCellData(i).equals(prId)) {
                    productqty += (int) colProductQty.getCellData(i);

                    obList.get(i).setPrductqty(productqty);
                    tblMaterialUsage.refresh();
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
                MaterialTableTm tm = tblMaterialUsage.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblMaterialUsage.getItems().remove(tm);
            }
        });
        obList.add(new MaterialTableTm(date, prId, productName, unitPrice, productqty, Id, materialType, userId, materialqty, btnDelete));
        tblMaterialUsage.setItems(obList);
    }

    private void setCellValueFactory() {
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colPrId.setCellValueFactory(new PropertyValueFactory("prId"));
        colProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colProductQty.setCellValueFactory(new PropertyValueFactory("prductqty"));
        colMid.setCellValueFactory(new PropertyValueFactory("Id"));
        colMaterialType.setCellValueFactory(new PropertyValueFactory("materialtype"));
        colUid.setCellValueFactory(new PropertyValueFactory("userId"));
        colMaterialqty.setCellValueFactory(new PropertyValueFactory("materialqty"));
        colOption.setCellValueFactory(new PropertyValueFactory("btnDelete"));

    }

    @FXML
    void txtmaterialqtyOnAction(ActionEvent event) {
        addbtnOnAction(event);
    }


}
