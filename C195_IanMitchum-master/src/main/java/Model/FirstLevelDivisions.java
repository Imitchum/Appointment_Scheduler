package Model;
import javafx.scene.control.SingleSelectionModel;

/**
 * This is the First Level Divisions model class
 */
public class FirstLevelDivisions extends SingleSelectionModel<String> {
    public int divisionID;
    public String divisionName;
    public int countryID;


    /**
     * Constructor for First Level Divisions
     * @param divisionID - division ID
     * @param divisionName - division name
     * @param countryID - country ID
     */
    public FirstLevelDivisions(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    /**
     * Constructor for division name
     * @param divisionName - division name
     */
    public FirstLevelDivisions(String divisionName) {
    }

    /**
     * Constructor for division ID
     * @param divisionId - division ID
     */
    public FirstLevelDivisions(int divisionId) {
    }

    /**
     * Returns string value of division name
     * @return - division name
     */
    @Override
    public String toString(){
        return(divisionName);
    }


    /**
     * This method returns division ID
     * @return - division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This method gets the division name
     * @return - division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * This method gets the country ID
     * @return - country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method gets the First Level Divisions model item by integer
     * @param i - integer
     * @return - null
     */
    @Override
    protected String getModelItem(int i) {
        return null;
    }

    /**
     * This method gets the item count
     * @return - 0
     */
    @Override
    protected int getItemCount() {
        return 0;
    }
}
