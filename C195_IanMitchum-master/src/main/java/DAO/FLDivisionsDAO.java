package DAO;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This has the methods to handle database interactions for the First Level Divisions class
 */
public class FLDivisionsDAO {
    /**
     * This method gets all divisions
     * @return - divisions
     */
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


    /**
     * This method gets the divisions by country
     * @param countryID - country iD
     * @return - divisions by country ID
     */
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
                FirstLevelDivisions D = new FirstLevelDivisions(dID, Division, countryID);
                divisions.add(D);
            }

        } catch(SQLException ex){

        }

        return divisions;
    }
}
