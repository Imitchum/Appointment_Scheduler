package DAO;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * This has the methods to handle database interactions for the appointments class
 */
public class AppointmentsDAO {
    /**
     * This method gets all appointments from the database
     * @return - all appointments
     */
    public static ObservableList<Appointments> getAllAppointments()
    {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointments ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next())
            {
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                Timestamp tstart = rs.getTimestamp("Start");
                LocalDateTime start = tstart.toLocalDateTime();
                Timestamp tend = rs.getTimestamp("End");
                LocalDateTime end = tend.toLocalDateTime();
                int appointmentCustomerID = rs.getInt("Customer_ID");
                int appointmentUserID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments A = new Appointments(appointmentID,appointmentTitle,appointmentDescription,appointmentLocation,appointmentType, start, end,appointmentCustomerID,appointmentUserID,contactID);
                appointments.add(A);

            }

        } catch(SQLException ex){

        }

        return appointments;
    }

    /**
     * This method gets appointments by week for the appointment tableview
     * @return - appointments by week
     */
    public static ObservableList<Appointments> getAppointmentsByWeek()
    {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * from appointments AS a WHERE Week(start) = Week(NOW()) AND YEAR(start) = YEAR(NOW()) ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next())
            {
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                Timestamp tstart = rs.getTimestamp("Start");
                LocalDateTime start = tstart.toLocalDateTime();
                Timestamp tend = rs.getTimestamp("End");
                LocalDateTime end = tend.toLocalDateTime();
                int appointmentCustomerID = rs.getInt("Customer_ID");
                int appointmentUserID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments A = new Appointments(appointmentID,appointmentTitle,appointmentDescription,appointmentLocation,appointmentType, start, end, appointmentCustomerID,appointmentUserID,contactID);
                appointments.add(A);

            }

        } catch(SQLException ex){

        }

        return appointments;
    }

    /**
     * This method gets appointments by month for the appointment tableview
     * @return - appointments by month
     */
    public static ObservableList<Appointments> getAppointmentsByMonth()
    {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * from appointments AS a WHERE Month(start) = Month(NOW()) AND YEAR(start) = YEAR(NOW())";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next())
            {
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                Timestamp tstart = rs.getTimestamp("Start");
                LocalDateTime start = tstart.toLocalDateTime();
                Timestamp tend = rs.getTimestamp("End");
                LocalDateTime end = tend.toLocalDateTime();
                int appointmentCustomerID = rs.getInt("Customer_ID");
                int appointmentUserID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments A = new Appointments(appointmentID,appointmentTitle,appointmentDescription,appointmentLocation,appointmentType, start, end, appointmentCustomerID,appointmentUserID,contactID);
                appointments.add(A);
            }

        } catch(SQLException ex){

        }
        return appointments;
    }

    /**
     * This method returns appointments by contact ID
     * @param contactID - contact ID
     * @return - appointments by contact ID
     */
    public ObservableList<Appointments> getApptByContactID(int contactID)
    {
        ObservableList<Appointments> appointments = FXCollections.observableArrayList();
        try{
            String sql = " SELECT * FROM appointments WHERE Contact_ID=? ";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setInt(1,contactID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                Timestamp tstart = rs.getTimestamp("Start");
                LocalDateTime start = tstart.toLocalDateTime();
                Timestamp tend = rs.getTimestamp("End");
                LocalDateTime end = tend.toLocalDateTime();
                int appointmentCustomerID = rs.getInt("Customer_ID");
                int appointmentUserID = rs.getInt("User_ID");
                contactID = rs.getInt("Contact_ID");

                Appointments A = new Appointments(appointmentID,appointmentTitle,appointmentDescription,appointmentLocation,appointmentType, start, end, appointmentCustomerID,appointmentUserID,contactID);
                appointments.add(A);

            }

        } catch(SQLException ex){

        }

        return appointments;
    }

    /**
     * This method adds appointments to the database
     * @param title - title
     * @param description - description
     * @param location - location
     * @param type - type
     * @param start - start
     * @param end - end
     * @param customerID - customer ID
     * @param userID - user ID
     * @param contactID - contact ID
     */
    public static void addAppointment(String title, String description, String location, String type, LocalDateTime start,LocalDateTime end, int customerID, int userID, int contactID){
        try{
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) Values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7,customerID);
            ps.setInt(8,userID);
            ps.setInt(9,contactID);
            ps.executeUpdate();
        } catch (SQLException e){e.printStackTrace();}
    }

    /**
     * This method updates appointments in the database
     * @param title - title
     * @param description - description
     * @param location - location
     * @param type - type
     * @param start - start
     * @param end - end
     * @param customerID - customer ID
     * @param userID - user ID
     * @param contactID - contact ID
     */
    public static void updateAppointment(String title, String description, String location, String type, LocalDateTime start,LocalDateTime end, int customerID, int userID, int contactID, int appointmentID){
        try{
            String sql = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, customerID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);
            ps.setInt(10,appointmentID);
            ps.executeUpdate();
        } catch (SQLException e){e.printStackTrace();}
    }

    /**
     * This method deletes appointmentts from the database using the appointment ID
     * @param appointmentID - appointment ID
     */
    public static void deleteAppointment(int appointmentID) {
        try{
            String sql = "DELETE FROM `appointments` WHERE Appointment_ID = ? ";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setInt(1,appointmentID);
            ps.execute();
        } catch(SQLException e){}
    }

}
