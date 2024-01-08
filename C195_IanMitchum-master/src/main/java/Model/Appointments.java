package Model;
import DAO.AppointmentsDAO;
import DAO.ContactsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/**
 * This is the Appointment model class
 */
public class Appointments {
    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime appointmentStartTime;
    private LocalDateTime appointmentEndTime;
    private int customerID;
    private int userID;
    public int contactID;


    /**
     * Appointments constructor
     * @param appointmentID - appointment ID
     * @param appointmentTitle - appointment title
     * @param appointmentDescription - appointment description
     * @param appointmentLocation - appointment location
     * @param appointmentType - appointment type
     * @param appointmentStartTime - appointment start time
     * @param appointmentEndTime - appointment end time
     * @param customerID - customer ID
     * @param userID - user ID
     * @param contactID - contact ID
     */

    public Appointments(int appointmentID, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime appointmentStartTime, LocalDateTime appointmentEndTime, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }


    /**
     * Method to get contact ID
     * @return - contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Method to get string value of customer ID
     * @return - customer ID
     */
    @Override
    public String toString(){return String.valueOf((customerID));}

    /**
     * Method to get appointment ID
     * @return - appointment ID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Method to get appointment title
     * @return - appointment title
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * Method to get appointment description
     * @return - appointment description
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * Method to get appointment location
     * @return - appointment location
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * Method to get appointment type
     * @return - appointment type
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Method to get appointment start time
     * @return - appointment start time
     */
    public LocalDateTime getAppointmentStartTime() {
        return appointmentStartTime;
    }

    /**
     * Method to get appointment end time
     * @return - appointment end time
     */
    public LocalDateTime getAppointmentEndTime() {
        return appointmentEndTime;
    }

    /**
     * Method to get customer ID
     * @return - customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Method to get user ID
     * @return - user ID
     */
    public int getUserID() {
        return userID;
    }

    public static ObservableList<Appointments> lookupAppts(String apptType)
    {
        ObservableList<Appointments> apptTypes = FXCollections.observableArrayList();
        ObservableList<Appointments> allAppts = AppointmentsDAO.getAllAppointments();

        for(Appointments appts: allAppts) {
            if(appts.appointmentType.contains(apptType)) {
                apptTypes.add(appts);
            }
        }
        return apptTypes;
    }

}


