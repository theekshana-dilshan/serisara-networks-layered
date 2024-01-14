package lk.ijse.controller;

        import com.jfoenix.controls.JFXTextField;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.geometry.Pos;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Alert;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.effect.BoxBlur;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;
        import javafx.util.Duration;
        import lk.ijse.bo.BOFactory;
        import lk.ijse.bo.custom.EmployeeBO;
        import lk.ijse.dto.CustomerDto;
        import lk.ijse.dto.EmployeeDto;
        import lk.ijse.dto.tm.EmployeeTm;
        import lk.ijse.dto.tm.StockTm;
        import org.controlsfx.control.Notifications;

        import java.io.IOException;
        import java.sql.SQLException;
        import java.util.List;
        import java.util.regex.Pattern;

public class EmployeeManageFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPosition;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtSearch;

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize (){
        setCellValueFactory();
        generateNextEmployeeId();
        loadAllEmployee();
        tableListener();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void loadAllEmployee(){
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = employeeBO.getAllEmployees();

            for (EmployeeDto dto : dtoList) {
                obList.add(
                        new EmployeeTm(
                                dto.getEmpId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getPosition(),
                                dto.getContact(),
                                dto.getSalary()
                        )
                );
            }
            tblEmployee.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddAsUserOnAction(ActionEvent event) throws IOException {
        BoxBlur blur = new BoxBlur(3, 3, 1);
        root.setEffect(blur);
        Parent parent = FXMLLoader.load(getClass().getResource("/view/AddUserForm.fxml"));
        Scene scene =new Scene(parent);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add User");
        stage.showAndWait();
        root.setEffect(null);
        stage.setOnCloseRequest(e -> {
            root.setEffect(null);
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String position = txtPosition.getText();
        String salary = txtSalary.getText();
        String userId = "U00-001";

        if(id.isEmpty() || name.isEmpty() || address.isEmpty() || contact.isEmpty() || position.isEmpty() || salary.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fill all fields");
            alert.showAndWait();
            return;
        }

        if(!validateEmployee()){
            return;
        }
        EmployeeDto employeeDto = new EmployeeDto(id, name, address,position, contact, salary, userId);

        try {
            boolean isAdded = employeeBO.saveEmployee(employeeDto);
            if (isAdded) {
                Notifications.create()
                        .graphic(new ImageView(new Image("/icons/icons8-check-mark-48.png")))
                        .text("Supplier Added")
                        .title("success")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
                clearFields();
                loadAllEmployee();
            } else {
                BoxBlur blur = new BoxBlur(3, 3, 1);
                root.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Somthing went wrong");
                alert.showAndWait();
                root.setEffect(null);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean validateEmployee(){

        boolean matches = Pattern.matches("[E][0-9\\-]{3,}", txtEmployeeId.getText());
        if (!matches){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid employee id");
            alert.showAndWait();
            return false;
        }

        boolean matches1 = Pattern.matches("^([ \\u00c0-\\u01ffa-zA-Z'\\-]{2,})+$", txtName.getText());
        if(!matches1){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid name");
            alert.showAndWait();
            return false;
        }

        boolean matches2 = Pattern.matches("[A-Za-z0-9'\\.\\-\\s\\,\\\\]{0,}", txtAddress.getText());
        if(!matches2){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid address");
            alert.showAndWait();
            return false;
        }

        boolean matches3 = Pattern.matches("^([ \\u00c0-\\u01ffa-zA-Z'\\-]{5,})+$", txtPosition.getText());
        if (!matches3) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid position");
            alert.showAndWait();
            return false;
        }

        boolean matches4 = Pattern.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$", txtContact.getText());
        if (!matches4) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid Contact number");
            alert.showAndWait();
            return false;
        }

        boolean matches5 = Pattern.matches("[0-9][1-9.]*[0-9]+[1-9]*", txtSalary.getText());
        if (!matches5) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid salary");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();

        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted) {
                BoxBlur blur = new BoxBlur(3, 3, 1);
                root.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Employee Deleted");
                alert.showAndWait();
                root.setEffect(null);
                clearFields();
                loadAllEmployee();
            } else {
                BoxBlur blur = new BoxBlur(3, 3, 1);
                root.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Somthing went wrong");
                alert.showAndWait();
                root.setEffect(null);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String position = txtPosition.getText();
        String salary = txtSalary.getText();
        String userId = "U00-001";

        EmployeeDto employeeDto = new EmployeeDto(id, name, address, contact, position, salary, userId);

        if(id.isEmpty() || name.isEmpty() || address.isEmpty() || contact.isEmpty() || position.isEmpty() || salary.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fill all fields");
            alert.showAndWait();
            return;
        }

        try {
            Boolean isUpdated = employeeBO.updateEmployee(employeeDto);
            if (isUpdated) {
                Notifications.create()
                        .graphic(new ImageView(new Image("/icons/icons8-check-mark-48.png")))
                        .text("Employee updated")
                        .title("success")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
                clearFields();
                loadAllEmployee();
            } else {
                BoxBlur blur = new BoxBlur(3, 3, 1);
                root.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Somthing went wrong");
                alert.showAndWait();
                root.setEffect(null);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        if (id!= null &&!id.isEmpty()) {
            try {
                EmployeeDto employeeDto = employeeBO.getEmployee(id);

                if(employeeDto != null){
                    txtEmployeeId.setText(employeeDto.getEmpId());
                    txtName.setText(employeeDto.getName());
                    txtAddress.setText(employeeDto.getAddress());
                    txtContact.setText(employeeDto.getContact());
                    txtPosition.setText(employeeDto.getPosition());
                    txtSalary.setText(employeeDto.getSalary());
                } else {
                    BoxBlur blur = new BoxBlur(3, 3, 1);
                    root.setEffect(blur);
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Employee not found");
                    alert.showAndWait();
                    root.setEffect(null);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Customer name is null or empty");
        }
    }

    private void generateNextEmployeeId() {
        try {
            String employeeId = employeeBO.generateNextEmployeeId();
            txtEmployeeId.setText(employeeId);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void tableListener() {
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(EmployeeTm row) {
        txtEmployeeId.setText(row.getEmpId());
        txtName.setText(row.getName());
        txtPosition.setText(String.valueOf(row.getPosition()));
        txtSalary.setText(String.valueOf(row.getSalary()));
        txtContact.setText(String.valueOf(row.getContact()));
        txtAddress.setText(String.valueOf(row.getAddress()));
    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtPosition.setText("");
        txtSalary.setText("");
        txtSearch.setText("");
    }
}

