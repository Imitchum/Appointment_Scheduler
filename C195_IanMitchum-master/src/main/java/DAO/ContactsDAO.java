package DAO;

import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsDAO {
    public static ObservableList<Contacts> getAllContacts()
    {
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM contacts ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int contactID =rs.getInt("Contact_ID");
                String contactName =rs.getString("Contact_Name");


                Contacts C = new Contacts(contactID,contactName);
                contacts.add(C);
            }

        } catch(SQLException ex){

        }

        return contacts;
    }

    public static ObservableList<Contacts> getContactID(int contactID)
    {
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Contact_ID FROM contacts ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                contactID =rs.getInt("Contact_ID");

                Contacts C = new Contacts(contactID);
                contacts.add(C);
            }

        } catch(SQLException ex){

        }

        return contacts;
    }

    public static ObservableList<Contacts> getContact() {
        ObservableList<Contacts> contacts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_ID, count(*) as total FROM appointments group by Contact_ID ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ContactID = rs.getInt("Contact_ID");
                int total = rs.getInt("Total");

                Contacts C = new Contacts(ContactID, total);
                contacts.add(C);

            }

        } catch (SQLException ex) {
        }

        return contacts;
    }

}
