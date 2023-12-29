package Model;

import javafx.scene.control.SingleSelectionModel;

public class FirstLevelDivisions extends SingleSelectionModel<String> {
    public int divisionID;
    public String divisionName;
    public int countryID;

    public FirstLevelDivisions(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    public FirstLevelDivisions(String divisionName) {
    }

    public FirstLevelDivisions(int divisionId) {
    }

    @Override
    public String toString(){
        return(divisionName);
    }

    public int getDivisionID() {
        return divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public int getCountryID() {
        return countryID;
    }

    @Override
    protected String getModelItem(int i) {
        return null;
    }

    @Override
    protected int getItemCount() {
        return 0;
    }
}
