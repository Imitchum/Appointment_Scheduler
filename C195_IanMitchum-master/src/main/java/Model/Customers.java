package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the customers model class
 */
public class Customers extends Countries{
     public int customerID;
     public String customerName;
     public String customerAddress;
     public String customerPostalCode;
     public String customerPhoneNumber;
     public String division;
     public int divisionId;
     public String country;

    /**
     * This observable list contains associated appointments
     */
    private ObservableList<Appointments> associatedAppts = FXCollections.observableArrayList();


    /**
     * This is the constructor for the customers
     * @param customerID - customer ID
     * @param customerName - customer name
     * @param customerAddress - customer address
     * @param customerPostalCode - customer postal code
     * @param customerPhoneNumber - customer phone number
     * @param country - customer country
     * @param division - customer division
     * @param divisionId - customer division ID
     */
    public Customers(int customerID, String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber,String country ,String division,int divisionId) {
        super();
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.country = country;
        this.division= division;
        this.divisionId = divisionId;
    }

    /**
     * This method gets division
     * @return - division
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method sets the division
     * @param division - division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * This method gets the country
     * @return - country
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method sets the country
     * @param country - country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Overloaded constructor for customers for customer ID and customer name
     * @param customerID - customer ID
     * @param customerName - customer name
     */
    public Customers(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }

    /**
     * This method gets the string value of customer name
     * @return customer name
     */
    @Override
    public String toString(){
        return customerName;
    }

    /**
     * This method gets the customer ID
     * @return - customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method sets the customer ID
     * @param customerID - customer ID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * This method gets the customer name
     * @return - customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method sets the customer name
     * @param customerName - customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * This method gets the customer address
     * @return - customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * This method sets the customer address
     * @param customerAddress - customer addres
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * This method gets the customer postal code
     * @return - customer postal code
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * This method sets the customer postal code
     * @param customerPostalCode - customer postal code
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * This method gets the customer phone number
     * @return - customer phone number
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * This method sets the customer phone number
     * @param customerPhoneNumber  -customer phone number
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * This method gets the division ID
     * @return - division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * This method sets the division ID
     * @param divisionId - division ID
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }


    /**
     * This will return an observable list of associated appointments.
     * @return
     */
    public ObservableList<Appointments> getAssociatedAppts() {
        return associatedAppts;
    }
}
