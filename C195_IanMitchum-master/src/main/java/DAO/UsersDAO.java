package DAO;

import Model.Contacts;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
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

    public static ObservableList<Users> getUserID(int userID) {
        ObservableList<Users> users = FXCollections.observableArrayList();
        try {
            String sql = "SELECT User_ID FROM users ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userID = rs.getInt("User_ID");


                Users U = new Users(userID);
                users.add(U);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public static Users login(String username, String password)
    {

        try{
            String sql = "SELECT * FROM users WHERE User_Name = ? AND password = ? ";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int userID = rs.getInt("User_ID");
                String userName =rs.getString("User_Name");
//                String password = rs.getString("Password");

                Users users = new Users(userID,userName,password);
                return users;

            }

        } catch(SQLException ex){

        }
        return null;
    }
}
