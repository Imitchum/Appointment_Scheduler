package Controller;

import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import DAO.ReportsDAO;
import Model.Appointments;
import Model.Contacts;
import Model.Reports;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * This is the reports controller, it has the methods for interacting with the reports tableview
 */
public class reportsPageController implements Initializable {
    @FXML
    private TableColumn<?, ?> reportContactTotal;
    @FXML
    private TableColumn<?, ?> reportAppointmentCustomerID;
    @FXML
    private TableColumn<?, ?> reportAppointmentDesc;
    @FXML
    private TableColumn<?, ?> reportAppointmentEnd;
    @FXML
    private TableColumn<?, ?> reportAppointmentId;
    @FXML
    private TableColumn<?, ?> reportAppointmentMonth;
    @FXML
    private TableColumn<?, ?> reportAppointmentStart;
    @FXML
    private TableColumn<?, ?> reportAppointmentTitle;
    @FXML
    private TableColumn<?, ?> reportContactID;
    @FXML
    private TableColumn<?, ?> reportTotal;
    @FXML
    private TableColumn<?, ?> reportType;
    @FXML
    private TableView<Appointments> reportAppointmentTbleView;
    @FXML
    private TableView<Reports> reportMonthTableView;
    @FXML
    private ComboBox<Contacts> reportContactCmboBx;
    @FXML
    private TableView<Contacts> reportContactTableView;


    Stage stage;
    Parent scene;

    /**
     * This method goes to the main appointments page
     * @param event - event
     * @throws IOException - handles exception handling
     */
    @FXML
    void onActionGoToAppointmentsPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/appointmentsPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method goes to the main customers page
     * @param event -event
     * @throws IOException - handles exception handling
     */
    @FXML
    void onActionGoToCustomersPage(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method goes to the main login screen
     * @param event - event
     * @throws IOException - handles exception handling
     */
    @FXML
    void onActionGoToMainLoginScreen(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainLogin.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method populates the tableview depending on which contact is selected
     * @param event - event
     */
    @FXML
    void onActionSelectContactByID(ActionEvent event) {
        AppointmentsDAO appointmentsDAO = new AppointmentsDAO();
        int contactID = reportContactCmboBx.getSelectionModel().getSelectedItem().getContactID();
        reportAppointmentTbleView.setItems(appointmentsDAO.getApptByContactID(contactID));
    }


    /**
     * This method sets up the tableviews for the reports page
     * @param url - url
     * @param resourceBundle - resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Appointments> apptList = AppointmentsDAO.getAllAppointments();
        reportAppointmentId.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        reportAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        reportAppointmentDesc.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        reportType.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        reportAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("appointmentStartTime"));
        reportAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("appointmentEndTime"));
        reportAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        ObservableList<Reports> rList = ReportsDAO.getAllReports();
        reportAppointmentMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        reportType.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        ObservableList<Contacts> CList = ContactsDAO.getContact();
        reportContactID.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        reportContactTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        reportContactTableView.setItems(CList);

        reportMonthTableView.setItems(rList);

        ObservableList<Contacts> allcontacts = ContactsDAO.getAllContacts();
        reportContactCmboBx.setItems(allcontacts);

    }
}
