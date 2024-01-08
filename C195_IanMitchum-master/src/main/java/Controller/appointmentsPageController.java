package Controller;
import DAO.AppointmentsDAO;
import Model.Appointments;
import Model.Contacts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the main appointments page controller
 */
public class appointmentsPageController implements Initializable {
    @FXML
    private TableView<Appointments> AppointmentsTableView;
    @FXML
    private TableColumn<?, ?> contactApptCol;
    @FXML
    private TableColumn<?, ?> appointmentCustomerIDCol;
    @FXML
    private TableColumn<?, ?> appointmentDescriptionCol;
    @FXML
    private TableColumn<?, ?> appointmentEndDateTimeCol;
    @FXML
    private TableColumn<?, ?> appointmentIDCol;
    @FXML
    private TableColumn<?, ?> appointmentLocationCol;
    @FXML
    private TableColumn<?, ?> appointmentStartDateTimeCol;
    @FXML
    private TableColumn<?, ?> appointmentTitleCol;
    @FXML
    private TableColumn<?, ?> appointmentTypeCol;
    @FXML
    private TableColumn<?, ?> appointmentUserIDCol;
    @FXML
    private TextField apptSearchTxt;
    Stage stage;
    Parent scene;

    /**
     * This method goes to the add appointment menu
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionAddAppointmentMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addAppointmentsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * This method deletes appointments when the user selects an appointment and clicks delete if no selection,  error message pops up
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) {
        Appointments appts = AppointmentsTableView.getSelectionModel().getSelectedItem();

        if(appts == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Appointment Alert");
            alert.setContentText("Please select an appointment");
            alert.showAndWait();
            return;
        }

        Appointments appointment = AppointmentsTableView.getSelectionModel().getSelectedItem();
        int appointmentID = appointment.getAppointmentID();
        AppointmentsDAO.deleteAppointment(appointmentID);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete Appointment Alert");
        alert.setContentText("Appointment ID: " + appointmentID + " Type: "+ appointment.getAppointmentType() + " has been canceled.");
        alert.showAndWait();
        AppointmentsTableView.setItems(AppointmentsDAO.getAllAppointments());
    }

    /**
     * This method shows all appointments when clicking the all appointments radio button
     * @param event - event
     */
    @FXML
    void onActionShowAllAppts(ActionEvent event) {
        ObservableList<Appointments> allAppointments = AppointmentsDAO.getAllAppointments();
        AppointmentsTableView.setItems(allAppointments);
    }

    /**
     * This method shows monthly appointments when clicking the monthly appointments radio button
     * @param event - event
     */
    @FXML
    void onActionShowMonthlyAppts(ActionEvent event) {
        ObservableList<Appointments> monthlyAppointments = AppointmentsDAO.getAppointmentsByMonth();
        AppointmentsTableView.setItems(monthlyAppointments);
    }

    /**
     * This method shows all weekly appointments when clicking the weekly appointments radio button
     * @param event - event
     */
    @FXML
    void onActionShowWeeklyAppts(ActionEvent event) {
        ObservableList<Appointments> weeklyAppts = AppointmentsDAO.getAppointmentsByWeek();
        AppointmentsTableView.setItems(weeklyAppts);
    }

    /**
     * This method goes to the customer page when the customers button is clicked
     * @param event - event
     * @throws IOException - event handling
     */
    @FXML
    void onActionGoToCustomerPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes to the main login page when the exit button is clicked
     * @param event - event
     * @throws IOException - event handling
     */
    @FXML
    void onActionGoToMainLogin(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainLogin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes to the reports page when the reports button is clicked
     * @param event - event
     * @throws IOException - event handling
     */
    @FXML
    void onActionGoToReportPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/reportsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes to the update appointment menu when the user makes a selection. It also sends the data from this controller to the update appointments controller
     * @param event - event
     * @throws IOException - event handling
     */
    @FXML
    void onActionUpdateAppointmentMenu(ActionEvent event) throws IOException {
        Appointments appts = AppointmentsTableView.getSelectionModel().getSelectedItem();

        if(appts == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Update Appointment Alert");
            alert.setContentText("Please select an appointment");
            alert.showAndWait();
            return;
        }

        FXMLLoader updateAppointmentLoader = new FXMLLoader();
        updateAppointmentLoader.setLocation(getClass().getResource("/View/updateAppointmentsPage.fxml"));
        Parent scene = updateAppointmentLoader.load();
        updateAppointmentsPageController AIController = updateAppointmentLoader.getController();
        Appointments selectedAppointments = AppointmentsTableView.getSelectionModel().getSelectedItem();
        AIController.sendAppointmentInfo(selectedAppointments);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSearchAppt(ActionEvent event) {
        String p = apptSearchTxt.getText();
        ObservableList<Appointments> apptSearch = Appointments.lookupAppts(p);

        try {
            if (apptSearch.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment Search");
                alert.setContentText("No appointments found with that type.");
                alert.showAndWait();
            } else {
                AppointmentsTableView.setItems(apptSearch);
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Appointment Search");
            alert.setContentText("Invalid input. Please enter a valid appointment type.");
            alert.showAndWait();
        }

    }

    /**
     * This method populates the appointments table with the data from the mysql database
     * @param url - url
     * @param resourceBundle - resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Appointments> Alist = AppointmentsDAO.getAllAppointments();
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentStartTime"));
        appointmentEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentEndTime"));
        appointmentCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        appointmentUserIDCol.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        contactApptCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        AppointmentsTableView.setItems(Alist);
    }
}
