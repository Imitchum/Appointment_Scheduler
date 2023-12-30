package Controller;
import DAO.CountryDAO;
import DAO.CustomersDAO;
import DAO.FLDivisionsDAO;
import Model.Countries;
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
 * This is the add customer page controller
 */
public class addCustomerPageController implements Initializable {
    @FXML
    private TextField CustomerPhoneNumberTxt;
    @FXML
    private ComboBox<Countries> addCustomerCountry;
    @FXML
    private ComboBox<FirstLevelDivisions> addCustomerState;
    @FXML
    private TextField customerAddressTxt;
    @FXML
    private TextField customerNameTxt;
    @FXML
    private TextField customerPostalCodeTxt;
    Stage stage;
    Parent scene;

    /**
     * This method will return to the main customer page if a user clicks cancel
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionReturnToCustomerInfo(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method handles saving the customer information to the database as well as handling any exceptions
     * @param event - event
     * @throws IOException - exception handling
     */
    @FXML
    void onActionSaveCustomer(ActionEvent event) throws IOException {
        try {
            String name = customerNameTxt.getText();
            String address = customerAddressTxt.getText();
            String postalCode = customerPostalCodeTxt.getText();
            String phone = CustomerPhoneNumberTxt.getText();
            FirstLevelDivisions divisionsID = addCustomerState.getValue();
            int did = divisionsID.getDivisionID();

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
            else if(addCustomerCountry == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please select a country");
                alert.showAndWait();
            }
            else if(addCustomerState == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Please select a state");
                alert.showAndWait();
            }
            else {
                CustomersDAO.addCustomer(name, address, postalCode, phone, did);
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Add customer alert");
                alert.setContentText("Customer added");
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
     * This handles the filtering of countries and first level divisions
     * @param event
     */
    @FXML
    void CountryComboBox(ActionEvent event) {
        ObservableList<FirstLevelDivisions> divisionsByCountry = FXCollections.observableArrayList();
        int countryID = addCustomerCountry.getValue().getCountryID();
        ObservableList<FirstLevelDivisions> divisions = FLDivisionsDAO.getDivisionsByCountry(countryID);
        divisions.forEach(d-> {
            if(d.getCountryID() == countryID) {
                divisionsByCountry.add(d);
            }
        });
        addCustomerState.setItems(divisionsByCountry);
    }

    /**
     * This populates the country combo box with the associated information.
     * @param url - url
     * @param resourceBundle - resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      ObservableList<Countries> allCountries = CountryDAO.getAllCountries();
      addCustomerCountry.setItems(allCountries);
    }
}
