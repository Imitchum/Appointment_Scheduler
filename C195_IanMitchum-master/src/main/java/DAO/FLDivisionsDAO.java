package DAO;

import Model.Countries;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FLDivisionsDAO {

    public static ObservableList<FirstLevelDivisions> getDivisions()
    {
        ObservableList<FirstLevelDivisions> divisions = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM first_level_divisions";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int dID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");


                FirstLevelDivisions D = new FirstLevelDivisions(dID, Division, countryID);
                divisions.add(D);

            }

        } catch(SQLException ex){

        }

        return divisions;
    }
    public static ObservableList<FirstLevelDivisions> getDivision(int divisionID)
    {
        ObservableList<FirstLevelDivisions> divisions = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Division_ID, Division, Country_ID from first_level_divisions WHERE Division_ID = ? ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setInt(1, divisionID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int dID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");


                FirstLevelDivisions D = new FirstLevelDivisions(divisionID, Division, countryID);
                divisions.add(D);

            }

        } catch(SQLException ex){

        }

        return divisions;
    }

    public static Countries getCountry(int divisionID) {
        ObservableList<FirstLevelDivisions> divisions = FXCollections.observableArrayList();
        Countries C;
        try {
            String sql = "SELECT  Countries.Country_ID, Country from first_level_divisions, Countries WHERE first_level_divisions.Division_ID = ? AND first_level_divisions.Country_ID = Countries.Country_ID ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setInt(1, divisionID);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {

                String Country = rs.getString("Country");
                int countryID = rs.getInt("Country_ID");


                C = new Countries(countryID, Country);
                return C;


            }

        } catch (SQLException ex) {

        }

        return null;
    }

    public static ObservableList<FirstLevelDivisions> getDivisionsByCountry(int countryID)
    {
        ObservableList<FirstLevelDivisions> divisions = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE Country_ID = ?";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setInt(1, countryID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int dID = rs.getInt("Division_ID");
                String Division = rs.getString("Division");
                //int countryID =rs.getInt("Country_ID");


                FirstLevelDivisions D = new FirstLevelDivisions(dID, Division, countryID);
                divisions.add(D);

            }

        } catch(SQLException ex){

        }

        return divisions;
    }
}
