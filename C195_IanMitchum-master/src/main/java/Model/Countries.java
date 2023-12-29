package Model;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;

import java.time.LocalDateTime;

public class Countries extends SingleSelectionModel<Countries> {
    public int countryID; //TODO: Juan's comment...this as well as all data members of classes should be declared private instead of public
    public String countryName; //TODO: Juan's comment...this as well as all data members of classes should be declared private instead of public

    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    public Countries(ObservableList<Countries> allCountries) {
    }

    public Countries(int divisionId) {
    }

    public Countries() {

    }

    @Override
    public String toString()
    {
        return(countryName);
    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    protected Countries getModelItem(int i) {
        return null;
    }

    @Override
    protected int getItemCount() {
        return 0;
    }

    public int countryID() {
        return countryID;
    }
}
