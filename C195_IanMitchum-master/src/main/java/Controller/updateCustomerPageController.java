package Controller;

import DAO.ContactsDAO;
import DAO.CountryDAO;
import DAO.CustomersDAO;
import DAO.FLDivisionsDAO;
import Model.Contacts;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class updateCustomerPageController implements Initializable {
    @FXML
    private TextField updateCustomerAddressTxt;
    @FXML
    private ComboBox<Countries> updateCustomerCountry;
    @FXML
    private ComboBox<FirstLevelDivisions> updateCustomerDivisions;
    @FXML
    private TextField updateCustomerIDTxt;
    @FXML
    private TextField updateCustomerNameTxt;
    @FXML
    private TextField updateCustomerPhoneNumberTxt;
    @FXML
    private TextField updateCustomerPostalCodeTxt;
    public int countryID;
    Stage stage;
    Parent scene;

    public void sendCustomerInfo(Customers selectedCustomer){

        updateCustomerIDTxt.setText(String.valueOf(selectedCustomer.getCustomerID()));
        updateCustomerNameTxt.setText(selectedCustomer.getCustomerName());
        updateCustomerAddressTxt.setText(selectedCustomer.getCustomerAddress());
        updateCustomerPostalCodeTxt.setText(selectedCustomer.getCustomerPostalCode());
        updateCustomerPhoneNumberTxt.setText(selectedCustomer.getCustomerPhoneNumber());

        ObservableList<Countries> countryList = CountryDAO.getAllCountries();
        ObservableList<FirstLevelDivisions> divisionList = FLDivisionsDAO.getDivisions();

        for (FirstLevelDivisions fld : divisionList) {
            if(selectedCustomer.getDivisionId() == fld.getDivisionID()) {
                countryID = fld.getCountryID();
                break;
            }
        }


        for(Countries countries: countryList) {
            if(countries.getCountryID() == countryID) {
                updateCustomerCountry.setValue(countries);
                break;
            }
        }


        ObservableList<FirstLevelDivisions> FLDList = FLDivisionsDAO.getDivisions();
            for (FirstLevelDivisions fld : FLDList) {
                if (fld.getDivisionID() == selectedCustomer.getDivisionId()) {
                    updateCustomerDivisions.setValue(new FirstLevelDivisions(fld.divisionID, fld.divisionName, fld.countryID));
                }
            }

    }

    @FXML
    void onActionReturntoCustomerInfoBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    //one of two lamda expressions, originally hard coded both country and divisions boxes creating several lines of unncessary code. Using a lamda reduced the amount of code by over 20 lines.
    @FXML
    void onActionUpdateCustomerCombo(ActionEvent event) {
        ObservableList<FirstLevelDivisions> divisionsByCountry = FXCollections.observableArrayList();
        int countryID = updateCustomerCountry.getValue().getCountryID();
        ObservableList<FirstLevelDivisions> divisions = FLDivisionsDAO.getDivisionsByCountry(countryID);
        divisions.forEach(d->{
            if(d.getCountryID() == countryID) {
                divisionsByCountry.add(d);
            }
        });
        updateCustomerDivisions.setItems(divisionsByCountry);
    }

    @FXML
    void onActionSaveUpdateBtn(ActionEvent event) throws IOException {
        try{

            String name = updateCustomerNameTxt.getText();
            String address = updateCustomerAddressTxt.getText();
            String postalCode = updateCustomerPostalCodeTxt.getText();
            String phone = updateCustomerPhoneNumberTxt.getText();
            int did = updateCustomerDivisions.getSelectionModel().getSelectedItem().getDivisionID();
            int customerID = Integer.parseInt(updateCustomerIDTxt.getText());

            if (name.isEmpty() || name.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please enter a name");
                alert.showAndWait();
            }
            else if(address.isEmpty() || address.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please enter a address");
                alert.showAndWait();
            }
            else if(postalCode.isEmpty() || postalCode.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please enter a postal code");
                alert.showAndWait();
            }
            else if(phone.isEmpty() || phone.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please enter a postal code");
                alert.showAndWait();
            }
            else if(updateCustomerCountry == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please select a country");
                alert.showAndWait();
            }
            else if(updateCustomerDivisions == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please select a state");
                alert.showAndWait();
            }
            else {
                CustomersDAO.updateCustomer(name, address, postalCode, phone, did, customerID);
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add customer alert");
            alert.setContentText("Customer country and division cannot be blank. Please select a country and division.");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Countries> allCountries = CountryDAO.getAllCountries();
        updateCustomerCountry.setItems(allCountries);
    }
}
