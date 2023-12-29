package Model;

import DAO.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This is the main class
 */
public class MainLogin extends Application {
    /**
     * This is the start method, it displays the user login screen upon running the program
     * @param stage - stage
     * @throws IOException - Input/ Output exception handling
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainLogin.class.getResource("/View/MainLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method handles the connection to the database upon starting the application
     * @param args -args
     */
    public static void main(String[] args) {
        DBConnection.openConnection();
        launch();
        DBConnection.closeConnection();


    }


}
