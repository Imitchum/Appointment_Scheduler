package Controller;
import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.CustomersDAO;
import DAO.UsersDAO;
import Helper.ApptOverlapHelper;
import Helper.TimeHelper;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This is the update appointments page controller
 */
public class updateAppointmentsPageController implements Initializable {
    @FXML
    private ComboBox<Contacts> updateAppointmentContactID;
    @FXML
    private ComboBox<Customers> updateAppointmentCustomerID;
    @FXML
    private TextField updateAppointmentDescriptionTxt;
    @FXML
    private DatePicker updateAppointmentEndDate;
    @FXML
    private ComboBox<LocalTime> updateApptEndTime;
    @FXML
    private ComboBox<LocalTime> updateApptStartTime;
    @FXML
    private DatePicker updateAppointmentStartDate;
    @FXML
    private TextField updateAppointmentIDTxt;
    @FXML
    private TextField updateAppointmentLocationTxt;
    @FXML
    private TextField updateAppointmentTitleTxt;
    @FXML
    private TextField updateAppointmentTypeTxt;
    @FXML
    private ComboBox<Users> updateAppointmentUserID;
    Stage stage;
    Parent scene;


    /**
     * This method gets the appointment information from the main appointments page and sets it in the appointment update page.
     * @param selectedAppointments - selectedAppointments
     */
    public void sendAppointmentInfo(Appointments selectedAppointments) {
        ObservableList<Contacts> contactNames = ContactsDAO.getAllContacts();
        updateAppointmentContactID.setItems(contactNames);

        ObservableList<Customers> customerNames = CustomersDAO.getAllCustomers();
        updateAppointmentCustomerID.setItems(customerNames);

        ObservableList<Users> allUsers = UsersDAO.getAllUsers();
        updateAppointmentUserID.setItems(allUsers);

        updateAppointmentIDTxt.setText(String.valueOf(selectedAppointments.getAppointmentID()));
        updateAppointmentTitleTxt.setText(selectedAppointments.getAppointmentTitle());
        updateAppointmentDescriptionTxt.setText(selectedAppointments.getAppointmentDescription());
        updateAppointmentLocationTxt.setText(selectedAppointments.getAppointmentLocation());
        updateAppointmentTypeTxt.setText(selectedAppointments.getAppointmentType());
        updateApptStartTime.setValue(selectedAppointments.getAppointmentStartTime().toLocalTime());
        updateAppointmentStartDate.setValue(selectedAppointments.getAppointmentStartTime().toLocalDate());
        updateApptEndTime.setValue(selectedAppointments.getAppointmentEndTime().toLocalTime());
        updateAppointmentEndDate.setValue(selectedAppointments.getAppointmentEndTime().toLocalDate());


        ObservableList<Contacts> contactList = ContactsDAO.getAllContacts();
        for (Contacts contacts : contactList) {
            if (contacts.contactID == selectedAppointments.getContactID()) {
                updateAppointmentContactID.setValue(new Contacts(contacts.contactID, contacts.contactName));
            }
        }

        ObservableList<Customers> customerList = CustomersDAO.getAllCustomers();
        for (Customers customers : customerList) {
            if (customers.customerID == selectedAppointments.getCustomerID()) {
                updateAppointmentCustomerID.setValue(new Customers(customers.customerID, customers.customerName));
            }
        }

        ObservableList<Users> usersList = UsersDAO.getAllUsers();
        for (Users users : usersList) {
            if (users.userID == selectedAppointments.getUserID()) {
                updateAppointmentUserID.setValue(new Users(users.userID));
            }
        }
    }

    /**
     * This method goes to the main appointments page when the user clicks the cancel button on the update appointments
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionReturnToAppointmentsPageBtn(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/appointmentsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method saves updated appointments and handles any exceptions
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionSaveUpdatedAppointmentBtn(ActionEvent event) throws IOException {
        try {
            int appointmentID = Integer.parseInt(updateAppointmentIDTxt.getText());
            String title = updateAppointmentTitleTxt.getText();
            String description = updateAppointmentDescriptionTxt.getText();
            String location = updateAppointmentLocationTxt.getText();
            String type = updateAppointmentTypeTxt.getText();
            LocalTime start = LocalTime.from(updateApptStartTime.getValue());
            LocalTime end = LocalTime.from(updateApptEndTime.getValue());
            int cuID = updateAppointmentCustomerID.getSelectionModel().getSelectedItem().getCustomerID();
            int usID = updateAppointmentUserID.getSelectionModel().getSelectedItem().getUserID();
            String cnID = String.valueOf(updateAppointmentContactID.getSelectionModel().getSelectedItem().getContactID());
            LocalDate startDate = updateAppointmentStartDate.getValue();
            LocalDate endDate = updateAppointmentEndDate.getValue();
            LocalDateTime startingTimePlusDate = LocalDateTime.of(startDate, start);
            LocalDateTime endingTimePlusDate = LocalDateTime.of(endDate, end);

            if (title.isEmpty() || title.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a title");
                alert.showAndWait();

            } else if (description.isBlank() || description.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a description");
                alert.showAndWait();

            } else if (location.isBlank() || location.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a location");
                alert.showAndWait();

            } else if (type.isBlank() || type.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a type");
                alert.showAndWait();

            } else if (updateApptStartTime == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a start time");
                alert.showAndWait();

            } else if (updateApptEndTime == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a end time");
                alert.showAndWait();

            } else if (updateAppointmentContactID == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a contact");
                alert.showAndWait();

            } else if (updateAppointmentCustomerID == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a customer");
                alert.showAndWait();

            } else if (updateAppointmentUserID == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a user");
                alert.showAndWait();

            } else if (updateAppointmentStartDate == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a start date");
                alert.showAndWait();

            } else if (updateAppointmentEndDate == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a end date");
                alert.showAndWait();

            } else if (ApptOverlapHelper.checkApptOverlap(startingTimePlusDate, endingTimePlusDate, cuID)) {
                return;
            } else {

                AppointmentsDAO.updateAppointment(title, description, location, type, startingTimePlusDate, endingTimePlusDate, cuID, usID, Integer.parseInt(cnID), appointmentID);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/appointmentsPage.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update appointment alert");
                alert.setContentText("Appointment updated");
                alert.showAndWait();
            }
        } catch(NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update appointment alert");
            alert.setContentText("The Contact, Customer ID, User ID, start time, and end time cannot be empty. Please make a selection.");
            alert.showAndWait();
        }

    }

    /**
     * This method populates the combo boxes as well as the business hours for the update appointments page
     * @param url -url
     * @param resourceBundle - resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Contacts> contactNames = ContactsDAO.getAllContacts();
        updateAppointmentContactID.setItems(contactNames);

        ObservableList<Customers> customerNames = CustomersDAO.getAllCustomers();
        updateAppointmentCustomerID.setItems(customerNames);

        ObservableList<Users> allUsers = UsersDAO.getAllUsers();
        updateAppointmentUserID.setItems(allUsers);

        updateApptStartTime.setItems(TimeHelper.getBusinessStart());
        updateApptEndTime.setItems(TimeHelper.getBusinessEnd());
    }

}
