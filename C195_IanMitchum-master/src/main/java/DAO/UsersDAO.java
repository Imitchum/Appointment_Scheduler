package DAO;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This has the methods to handle database interactions for the users class
 */
public class UsersDAO {
    /**
     * This method gets all users from the database
     * @return - users
     */
    public static ObservableList<Users> getAllUsers()
    {
        ObservableList<Users> users = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM users ";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int userID =rs.getInt("User_ID");
                String userName =rs.getString("User_Name");
                String password = rs.getString("Password");
                Users U = new Users(userID,userName,password);
                users.add(U);
            }

        } catch(SQLException ex){

        }
        return users;
    }

    /**
     * This method returns the username and password from the database
     * @param username - username
     * @param password - password
     * @return - username, password
     */
    public static Users login(String username, String password)
    {
        try{
            String sql = "SELECT * FROM users WHERE User_Name = ? AND password = ? ";
            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                int userID = rs.getInt("User_ID");
                String userName =rs.getString("User_Name");
                return new Users(userID,userName,password);
            }

        } catch(SQLException ex){

        }
        return null;
    }
}
