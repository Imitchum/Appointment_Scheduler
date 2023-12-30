package Controller;
import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.CustomersDAO;
import DAO.UsersDAO;
import Helper.ApptOverlapHelper;
import Helper.TimeHelper;
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
import java.time.*;
import java.util.ResourceBundle;

/**
 * This is the controller for the add appointments page
 */
public class addAppointmentsPageController implements Initializable {
    @FXML
    private ComboBox<Contacts> addappointmentContactID;
    @FXML
    private ComboBox<Customers> addappointmentCustomerID;
    @FXML
    private TextField addappointmentDescriptionTxt;
    @FXML
    private DatePicker addappointmentEndDate;
    @FXML
    private TextField addappointmentLocationTxt;
    @FXML
    private DatePicker addappointmentStartDate;
    @FXML
    private ComboBox<LocalTime> addApptEndTime;
    @FXML
    private TextField addAppointmentIDTxt;
    @FXML
    private ComboBox<LocalTime> addAppointmentStartTime;
    @FXML
    private TextField addappointmentTitleTxt;
    @FXML
    private TextField addappointmentTypeTxt;
    @FXML
    private ComboBox<Users> addappointmentUserID;
    Stage stage;
    Parent scene;

    /**
     * This method returns to the main appointments page, if the user clicks cancel
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionReturntoAppointmentsPage(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cancel Appointment Alert");
        alert.setContentText("Appointment canceled");
        alert.showAndWait();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/appointmentsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method handles saving an appointment from the add appointments page as well as handles exceptions
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    public void onActionSaveAppointment(ActionEvent event) throws IOException {
        try {
            String title = addappointmentTitleTxt.getText();
            String description = addappointmentDescriptionTxt.getText();
            String location = addappointmentLocationTxt.getText();
            String type = addappointmentTypeTxt.getText();
            LocalTime start = addAppointmentStartTime.getSelectionModel().getSelectedItem();
            LocalTime end = addApptEndTime.getSelectionModel().getSelectedItem();
            int cuID = addappointmentCustomerID.getSelectionModel().getSelectedItem().getCustomerID();
            int usID = addappointmentUserID.getSelectionModel().getSelectedItem().getUserID();
            int cnID = addappointmentContactID.getSelectionModel().getSelectedItem().getContactID();
            LocalDate startDate = addappointmentStartDate.getValue();
            LocalDate endDate = addappointmentEndDate.getValue();
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

            } else if (addAppointmentStartTime== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a start time");
                alert.showAndWait();

            } else if (addApptEndTime== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please enter a end time");
                alert.showAndWait();

            } else if (addappointmentContactID == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a contact");
                alert.showAndWait();


            } else if (addappointmentCustomerID == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a customer");
                alert.showAndWait();


            } else if (addappointmentUserID == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a user");
                alert.showAndWait();


            } else if (addappointmentStartDate == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a start date");
                alert.showAndWait();

            } else if (addappointmentEndDate == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Please select a end date");
                alert.showAndWait();

            } else if(ApptOverlapHelper.checkApptOverlap(startingTimePlusDate,endingTimePlusDate, cuID)) {return;}

            else {
                AppointmentsDAO.addAppointment(title, description, location, type, startingTimePlusDate, endingTimePlusDate, cuID, usID, cnID);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/appointmentsPage.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add appointment alert");
                alert.setContentText("Appointment added");
                alert.showAndWait();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add appointment alert");
            alert.setContentText("The Contact, Customer ID, User ID, start time, and end time cannot be empty. Please make a selection.");
            alert.showAndWait();
        }
    }


    /**
     * This method populates all combo boxes as well as start date, end date and start and end times
     * @param url - url
     * @param resourceBundle - resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            ObservableList<Contacts> contactNames = ContactsDAO.getAllContacts();
            addappointmentContactID.setItems(contactNames);

            ObservableList<Customers> customerNames = CustomersDAO.getAllCustomers();
            addappointmentCustomerID.setItems(customerNames);

            ObservableList<Users> allUsers = UsersDAO.getAllUsers();
            addappointmentUserID.setItems(allUsers);

            //Start + End date settings
            addappointmentStartDate.setValue(LocalDate.now());
            addappointmentEndDate.setValue(LocalDate.now());
            addAppointmentStartTime.setItems(TimeHelper.getBusinessStart());
            addApptEndTime.setItems(TimeHelper.getBusinessEnd());

    }

    /**
     * This is the event handler for the contact combo box
     * @param actionEvent - handler
     */
    public void addContactNames(ActionEvent actionEvent) {}

    /**
     * This is the event handler for the customer ID combo box
     * @param actionEvent - handler
     */
    public void addCustomerID(ActionEvent actionEvent) {}

    /**
     * This is the event handler for the user ID combo box
     * @param actionEvent - handler
     */
    public void addUserID(ActionEvent actionEvent) {}
}
