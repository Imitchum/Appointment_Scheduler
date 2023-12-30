package Controller;
import DAO.AppointmentsDAO;
import DAO.CustomersDAO;
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

/**
 * This is the customer info page controller, it populates the tableview and sends information to the add and update customer controllers
 */
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

    /**
     * This method exits the customer page when the user clicks the exit button
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void OnActionExitCustomerPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainLogin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method goes to the reports page when the user clicks the reports button
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void OnActionReportsPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/reportsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes to the add customer page when the user selects a customer and clicks the add button
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void addCustomerPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addCustomerPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method deletes a customer when the user selects a customer, it first checks if there are any associated appointments first and confirms before deleting.
     * Lamda - line 118 - two of two lamdas. It checks if the customer id of each appointment equals the chooseCustomer variable. It allows less code to perform the same action improving readability
     * Lamda - line 131 - Defines the condition to filter. It checks if the customer ID of all appointments matches the selected customer ID.
     * @param event - event
     */
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

        // The second lamda
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

    /**
     * This method goes to the main appointments page when the appointments button is clicked
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionAppointmentsForm(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/appointmentsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method goes to the update customer page when the user selects a customer and clicks the update button
     * @param event - event
     * @throws IOException - exception handling
     */
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


    /**
     * This method populates the customer tableview with the data from the mysql database
     * @param url - url
     * @param resourceBundle - resourceBundle
     */
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
