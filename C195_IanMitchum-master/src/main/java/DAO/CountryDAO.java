package DAO;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This has the methods to handle database interactions with the countries class
 */
public class CountryDAO {
    /**
     * This method gets all countries from the database
     * @return - countries
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countries = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country_ID, Country from countries ";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                countries.add(C);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

}
