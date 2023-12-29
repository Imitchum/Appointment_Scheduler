module c195im.c195_ianmitchum {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;


    opens Model to javafx.fxml;
    exports Model;
    exports Controller;
    opens Controller to javafx.fxml;
    opens View to javafx.fxml;

}