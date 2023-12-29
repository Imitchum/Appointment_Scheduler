package Controller;

import DAO.AppointmentsDAO;
import DAO.CustomersDAO;
import DAO.DBConnection;
import Model.Appointments;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerInfoPageController implements Initializable {
    @FXML
    private TableView<Customers> customerTableView;
    @FXML
    private Button addCustomerBtn;
    @FXML
    private TableColumn<?, ?> customerAddressCol;
    @FXML
    private TableColumn<?, ?> customerCountryCol;
    @FXML
    private Button customerAppointmentsBtn;
    @FXML
    private TableColumn<?, ?> customerIDCol;
    @FXML
    private TableColumn<?, ?> customerDivisionCol;
    @FXML
    private TableColumn<?, ?> customerNameCol;
    @FXML
    private Button customerPageExitBtn;
    @FXML
    private TableColumn<?, ?> customerPhoneNumberCol;
    @FXML
    private TableColumn<?, ?> customerPostalCodeCol;
    @FXML
    private Button customerReportsBtn;
    @FXML
    private Button deleteCustomerBtn;
    @FXML
    private Button updateCustomerBtn;

    Stage stage;
    Parent scene;

    @FXML
    void OnActionExitCustomerPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainLogin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void OnActionReportsPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/reportsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void addCustomerPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addCustomerPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void deleteCustomer(ActionEvent event) {
        int numOfAssociatedAppts = 0;

        ObservableList<Appointments> associatedAppts = AppointmentsDAO.getAllAppointments();
        Customers customer = customerTableView.getSelectionModel().getSelectedItem();

        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Customer Alert");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
            return;
        }

        int chooseCustomer = customerTableView.getSelectionModel().getSelectedItem().getCustomerID();

        // The second lamda. It checks if the customer id of each appointment equals the chooseCustomer variable.
        numOfAssociatedAppts = (int) associatedAppts.stream()
                .filter(assocAppt -> assocAppt.getCustomerID() == chooseCustomer)
                .count();

        if (numOfAssociatedAppts > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer Alert");
            alert.setContentText("Customer has appointments, delete appointments first. Click OK to Delete, Cancel to exit");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                associatedAppts.stream()
                        .filter(appts -> appts.getCustomerID() == chooseCustomer)
                        .forEach(appts -> AppointmentsDAO.deleteAppointment(appts.getCustomerID()));
                CustomersDAO.deleteCustomer(customerTableView.getSelectionModel().getSelectedItem().getCustomerID());
                customerTableView.setItems(CustomersDAO.getAllCustomers());
            } else if (alert.getResult() == ButtonType.CANCEL) {
                alert.close();
            }
        }

        if (numOfAssociatedAppts == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Customer");
            alert.setContentText("Customer deleted");
            alert.showAndWait();

            ButtonType result = alert.getResult();
            if (result == ButtonType.OK) {
                CustomersDAO.deleteCustomer(customerTableView.getSelectionModel().getSelectedItem().getCustomerID());
                customerTableView.setItems(CustomersDAO.getAllCustomers());
            } else if (result == ButtonType.CANCEL) {
                alert.close();
            }
        }


    }

    @FXML
    void onActionAppointmentsForm(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/appointmentsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void updateCustomerPage(ActionEvent event) throws IOException {
        Customers customer = customerTableView.getSelectionModel().getSelectedItem();

        if(customer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Customer Alert");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
            return;
        }

        Customers selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        FXMLLoader updateCustomerLoader = new FXMLLoader();
        updateCustomerLoader.setLocation(getClass().getResource("/View/updateCustomerPage.fxml"));
        updateCustomerLoader.load();

        updateCustomerPageController CIController = updateCustomerLoader.getController();
        CIController.sendCustomerInfo(selectedCustomer);


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = updateCustomerLoader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {


        ObservableList<Customers> Clist = CustomersDAO.getAllCustomers();
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("CustomerPostalCode"));
        customerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("CustomerPhoneNumber"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        customerTableView.setItems(Clist);
    }
}
