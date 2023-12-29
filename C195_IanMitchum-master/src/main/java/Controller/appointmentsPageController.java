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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    Stage stage;
    Parent scene;

    @FXML
    void onActionAddAppointmentMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addAppointmentsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

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

    @FXML
    void onActionShowAllAppts(ActionEvent event) {
        ObservableList<Appointments> allAppointments = AppointmentsDAO.getAllAppointments();
        AppointmentsTableView.setItems(allAppointments);
    }

    @FXML
    void onActionShowMonthlyAppts(ActionEvent event) {
        ObservableList<Appointments> monthlyAppointments = AppointmentsDAO.getAppointmentsByMonth();
        AppointmentsTableView.setItems(monthlyAppointments);
    }

    @FXML
    void onActionShowWeeklyAppts(ActionEvent event) {
        ObservableList<Appointments> weeklyAppts = AppointmentsDAO.getAppointmentsByWeek();
        AppointmentsTableView.setItems(weeklyAppts);
    }

    @FXML
    void onActionGoToCustomerPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionGoToMainLogin(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainLogin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionGoToReportPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/reportsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

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
