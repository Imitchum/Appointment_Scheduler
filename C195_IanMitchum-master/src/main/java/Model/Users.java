package Model;




public class Users {

    public String userName;
    public int userID;
    public String userPassword;



    public Users(int userID, String userName, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    //Used to get User ID for updateApptController method
    public Users(int userID) {
        this.userID = userID;
    }


    @Override
    public String toString(){
        return String.valueOf((userID));
    }



    public String getUserName() {
        return userName;
    }



    public int getUserID() {
        return userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
