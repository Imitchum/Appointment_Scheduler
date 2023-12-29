package Model;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;


/**
 * This is the Countries model class
 */
public class Countries extends SingleSelectionModel<Countries> {
    private int countryID;
    private String countryName;


    /**
     * Constructor for Countries
     * @param countryID - country ID
     * @param countryName - country name
     */
    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }


    /**
     * ObservableList to get all countries
     * @param allCountries - all countries
     */
    public Countries(ObservableList<Countries> allCountries) {
    }

    /**
     * Constructor for countries division ID
     * @param divisionId - division ID
     */
    public Countries(int divisionId) {
    }

    /**
     * Constructor for countries that's associated with the customers model class
     */
    public Countries() {

    }

    /**
     * Gets string value of country name
     * @return - country name
     */
    @Override
    public String toString()
    {
        return(countryName);
    }


    /**
     * Method to get country ID
     * @return - country ID
     */
    public int getCountryID() {
        return countryID;
    }


    /**
     * Method to get model item
     * @param i - integer
     * @return - null
     */
    @Override
    protected Countries getModelItem(int i) {
        return null;
    }

    /**
     * Method to get item count
     * @return - 0
     */
    @Override
    protected int getItemCount() {
        return 0;
    }

}
