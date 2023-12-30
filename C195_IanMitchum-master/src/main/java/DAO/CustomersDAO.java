package DAO;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This has the methods for the database to interact with the customer class
 */
public class CustomersDAO {
    /**
     * This method gets all the customers from the database
     * @return - all customers
     */
    public static ObservableList<Customers> getAllCustomers()
    {
        ObservableList<Customers> customers = FXCollections.observableArrayList();
        try{
            String sql = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone, customers.Division_ID, first_level_divisions.Division, first_level_divisions.Country_ID, countries.Country FROM customers JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID ORDER BY customers.Customer_ID ";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_code");
                String customerPhoneNumber = rs.getString("Phone");
                String country = rs.getString("Country");
                String division = rs.getString("Division");
                int divisionID = rs.getInt("Division_ID");
                Customers C = new Customers(customerID,customerName,customerAddress,customerPostalCode, customerPhoneNumber,country ,division,divisionID );
                customers.add(C);
            }

        } catch(SQLException ex){}
        return customers;
    }


    /**
     * This method adds customers to the database
     * @param customerName - customerName
     * @param Address - Address
     * @param postalCode - PostalCode
     * @param Phone - Phone
     * @param divisionID - divisionID
     */
    public static void addCustomer(String customerName, String Address, String postalCode, String Phone, int divisionID){
        try{
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) Values(?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, Address);
            ps.setString(3, postalCode);
            ps.setString(4, Phone);
            ps.setInt(5, divisionID);
            ps.execute();
        } catch (SQLException e){}
    }

    /**
     * This method updates customers in the database
     * @param customerName - customerName
     * @param Address - Address
     * @param postalCode - postalCode
     * @param Phone - Phone
     * @param divisionID - divisionID
     * @param customerID - customerID
     */
    public static void updateCustomer(String customerName, String Address, String postalCode, String Phone, int divisionID, int customerID){
        try{
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID  = ? WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, Address);
            ps.setString(3, postalCode);
            ps.setString(4, Phone);
            ps.setInt(5, divisionID);
            ps.setInt(6, customerID);
            ps.execute();

        } catch (SQLException e){}
    }

    /**
     * this method deletes the customer from the database
     * @param customerID - customer ID
     */
    public static void deleteCustomer(int customerID) {
        try{
            String sql = "DELETE FROM `customers` WHERE Customer_ID = ? ";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setInt(1,customerID);
            ps.execute();

        } catch(SQLException e){}
    }
}
