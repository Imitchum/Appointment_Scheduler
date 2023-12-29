package Helper;

import DAO.AppointmentsDAO;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeHelper {
    //method to change combo boxes to business hours 8a-10p
    //method to change timezones
    private static ObservableList<LocalTime> businessStart = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> businessEnd = FXCollections.observableArrayList();
    private static void businessHours() {
        ZonedDateTime easternStart = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8,0) , ZoneId.of("America/New_York"));
        LocalDateTime localStart = easternStart.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime localEnd = localStart.plusHours(14);

        while(localStart.isBefore(localEnd)) {
            businessStart.add(localStart.toLocalTime());
            localStart = localStart.plusMinutes(15);
            businessEnd.add(localStart.toLocalTime());
        }
    }

    public static ObservableList<LocalTime> getBusinessStart() {
        if(businessStart.isEmpty()) {
            businessHours();
        }
        return businessStart;
    }

    public static ObservableList<LocalTime> getBusinessEnd() {
        if(businessEnd.isEmpty()) {
            businessHours();
        }
        return businessEnd;
    }

    public static void upcomingApptAlert() {
        LocalDateTime currentTime = LocalDateTime.now();
        ZonedDateTime currentTimeZone = currentTime.atZone(ZoneId.systemDefault());
        LocalDateTime currentTimePlus15 = currentTime.plusMinutes(15);
        ZonedDateTime utcTimeZone = currentTimeZone.withZoneSameInstant(ZoneId.of("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        String formattedCurrentTime = currentTime.format(formatter);

        boolean hasValidAppointment = false;

        ObservableList<Appointments> allAppointments = AppointmentsDAO.getAllAppointments();
        for (int i = 0, allAppointmentsSize = allAppointments.size(); i < allAppointmentsSize; i++) {
            Appointments appointment = allAppointments.get(i);
            LocalDateTime startTime = appointment.getAppointmentStartTime();

            if ((startTime.isAfter(currentTime) || startTime.isEqual(currentTimePlus15)) && (startTime.isBefore(currentTimePlus15) || startTime.isEqual(currentTime))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setContentText("Appointment " + appointment.getAppointmentID() + " begins on " + appointment.getAppointmentStartTime().format(formatter));
                alert.showAndWait();
                hasValidAppointment = true;
            }
        }

        if (!hasValidAppointment) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment Alert");
            alert.setContentText("No upcoming appointments");
            alert.showAndWait();
        }
    }
}
