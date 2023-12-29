package Model;


/**
 * This is the Users model class
 */
public class Users {
    public String userName;
    public int userID;
    public String userPassword;


    /**
     * This is the Users constructor
     * @param userID - user ID
     * @param userName - user Name
     * @param userPassword - user password
     */
    public Users(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * This is the constructor for the user ID
     * @param userID - user ID
     */
    public Users(int userID) {
        this.userID = userID;
    }


    /**
     * This method gets the string value of the user ID
     * @return - user ID
     */
    @Override
    public String toString(){
        return String.valueOf((userID));
    }


    /**
     * This method gets the username
     * @return - user name
     */
    public String getUserName() {
        return userName;
    }


    /**
     * This method gets the user ID
     * @return - user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This method gets the user password
     * @return - user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This method sets the username
     * @param userName - user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method sets the user ID
     * @param userID - user ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * This method sets the user password
     * @param userPassword - userpassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
