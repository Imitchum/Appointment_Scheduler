package Model;
import java.time.LocalDateTime;


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



    public int getContactID() {
        return contactID;
    }
    @Override
    public String toString(){return String.valueOf((customerID));}
    public int getAppointmentID() {
        return appointmentID;
    }
    public String getAppointmentTitle() {
        return appointmentTitle;
    }
    public String getAppointmentDescription() {
        return appointmentDescription;
    }
    public String getAppointmentLocation() {
        return appointmentLocation;
    }
    public String getAppointmentType() {
        return appointmentType;
    }
    public LocalDateTime getAppointmentStartTime() {
        return appointmentStartTime;
    }
    public LocalDateTime getAppointmentEndTime() {
        return appointmentEndTime;
    }
    public int getCustomerID() {
        return customerID;
    }
    public int getUserID() {
        return userID;
    }

}


