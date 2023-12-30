package Helper;
import DAO.AppointmentsDAO;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.time.LocalDateTime;


/**
 * This method checks for appointment overlaps
 */
public class ApptOverlapHelper {

    /**
     * This checks for appointment overlaps using the appointment start time, end time, and customer ID
     * @param apptStart - appointment start time
     * @param apptEnd - appointment end time
     * @param cusID - customer ID
     * @return - true if overlap found, otherwise false if not
     */
    public static boolean checkApptOverlap(LocalDateTime apptStart, LocalDateTime apptEnd, int cusID) {
        ObservableList<Appointments> allAppointments = AppointmentsDAO.getAllAppointments();

        for (Appointments appts : allAppointments) {
            LocalDateTime apptStartTime = appts.getAppointmentStartTime();
            LocalDateTime apptEndTime = appts.getAppointmentEndTime();

            if (cusID != appts.getCustomerID()) continue;

            if((apptStartTime.isAfter(apptStart) || apptStartTime.isEqual(apptStart)) && apptStartTime.isBefore(apptEnd))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Appointment time conflict, please choose a different time.");
                alert.showAndWait();
                return true;
            }
           else if(apptEndTime.isAfter(apptStart) && (apptEndTime.isBefore(apptEnd) || apptEndTime.isEqual(apptEnd)))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Appointment time conflict, please choose a different time.");
                alert.showAndWait();
                return true;
            }
               else if((apptStartTime.isBefore(apptStart) || apptStartTime.isEqual(apptStart)) && (apptEndTime.isAfter(apptEnd) || apptEndTime.isEqual(apptEnd)))
             {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Appointment time conflict, please choose a different time.");
                alert.showAndWait();
                return true;
            }

        }

        return false;
    }


}
