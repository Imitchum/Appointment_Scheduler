package DAO;

import Model.Countries;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {
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
            //TODO: Juan's comment... catching an exception and doing nothing is typically a bad idea. THere are exceptions, yet this is not one of them
            e.printStackTrace();
        }

        return countries;
    }




}
