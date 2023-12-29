package Controller;

import DAO.CountryDAO;
import DAO.UsersDAO;
import Helper.TimeHelper;
import Model.Countries;
import Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainLoginController implements Initializable {
    @FXML
    private PasswordField mainLoginPasswordTxt;
    @FXML
    private Button exitBtn;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Label tzLbl;
    @FXML
    private Button signInBtn;
    @FXML
    private TextField userNameTxt;
    @FXML
    private Label loginLbl;
    @FXML
    private Label passwordLbl;
    @FXML
    private Label regionLbl;
    @FXML
    private Label usernameLbl;

    Stage stage;
    Parent scene;

    @FXML
    void OnActionCloseProgram(ActionEvent event) {System.exit(0);}



    @FXML
    void onActionGoToCustomersPage(ActionEvent event) throws IOException {
        //gets timestamp for current time and date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String timestamp = now.format(formatter);

        //file writer that records login activity
        String filename = "login_activity.txt";
        FileWriter fWriter = new FileWriter(filename, true);
        PrintWriter pWriter = new PrintWriter(fWriter);


        Users userlgn = UsersDAO.login(userNameTxt.getText(), mainLoginPasswordTxt.getText());

        if(userlgn != null) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/CustomerInfoPage.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            //login alert/ 15 min if available or not available
            TimeHelper.upcomingApptAlert();

            //login activity successful login
            pWriter.println("User login successful on " + timestamp);

        } else if(Locale.getDefault().getLanguage().equals("fr")) {
            ResourceBundle rb = ResourceBundle.getBundle("Resource/fre_fr", Locale.getDefault());
            //french pop dialog invalid user/ alert box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login alert");
            alert.setContentText(rb.getString("Incorrect") + " " + rb.getString("Username") +" "+ rb.getString("And")
                    + rb.getString("Or") + " " + rb.getString("Password") + "." +
                    rb.getString("Please") + " " + rb.getString("Try") +" " +  rb.getString("Again") + ".");
            alert.showAndWait();

            // log to login activity unsuccessful login
            pWriter.println("User login unsuccessful on " + timestamp);
        }
        else {
            //pop dialog invalid user/ alert box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login alert");
            alert.setContentText("Incorrect username and or password. Please try try again.");
            alert.showAndWait();

            // log to login activity unsuccessful login
            pWriter.println("User login unsuccessful on " + timestamp);
        }
        pWriter.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tzLbl.setText(String.valueOf(ZoneId.systemDefault()));
        if (Locale.getDefault().getLanguage().equals("fr")) {
            ResourceBundle rb = ResourceBundle.getBundle("Resource/fre_fr", Locale.getDefault());
            usernameLbl.setText(rb.getString("Username"));
            passwordLbl.setText(rb.getString("Password"));
            regionLbl.setText(rb.getString("Region"));
            signInBtn.setText(rb.getString("SignIn"));
            exitBtn.setText(rb.getString("Exit"));
            loginLbl.setText(rb.getString("Login"));
        }

    }

}

