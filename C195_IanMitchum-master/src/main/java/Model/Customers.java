package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customers extends Countries{
     public int customerID;
     public String customerName;
     public String customerAddress;
     public String customerPostalCode;
     public String customerPhoneNumber;
     public String division;


    private ObservableList<Appointments> associatedAppts = FXCollections.observableArrayList();


    public int divisionId;
     public String country;

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

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customers(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
    }


    @Override
    public String toString(){
        return customerName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public ObservableList<Appointments> getAssociatedAppts() {
        return associatedAppts;
    }
}
