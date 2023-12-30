package Controller;
import DAO.CountryDAO;
import DAO.CustomersDAO;
import DAO.FLDivisionsDAO;
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

/**
 * This is the update customer page controller it takes the data sent from the main customers page and sets it in all the fields of the update customers page
 */
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

    /**
     * This method takes the information sent from the main customers controller and sets it in all fields and combo boxes in the update customer page
     * @param selectedCustomer
     */
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

    /**
     * This method goes back to the main customer page when the user clicks cancel
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionReturntoCustomerInfoBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * This method handles the filtering of the country and divisions combo boxes
     * Lambda - line 108 - 1 of 2 lambdas. originally hard coded both country and divisions boxes creating several lines of unncessary code. Using a lamda reduced the amount of code by over 20 lines.
     * @param event - event
     */
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

    /**
     * This method saves the updated information in the update customer page controller when the user clicks save
     * @param event - event
     * @throws IOException - exception handling
     */
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

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update customer alert");
                alert.setContentText("Customer updated");
                alert.showAndWait();
            }

        } catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add customer alert");
            alert.setContentText("Customer country and division cannot be blank. Please select a country and division.");
            alert.showAndWait();
        }
    }


    /**
     * This method sets the countries to the combo boxes using an observable list
     * @param url - url
     * @param resourceBundle - resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Countries> allCountries = CountryDAO.getAllCountries();
        updateCustomerCountry.setItems(allCountries);
    }
}
