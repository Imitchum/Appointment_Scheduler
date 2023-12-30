package DAO;
import Model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This has the methods to handle database interactions for the reports class
 */
public class ReportsDAO {
    /**
     *This method gets the total of reports by month and groups them by month and type
     * @return - reports
     */
    public static ObservableList<Reports> getAllReports() {
        ObservableList<Reports> reports = FXCollections.observableArrayList();
        try {
            String sql = "SELECT month(start), type, count(*) as total FROM appointments GROUP BY month(start), type";

            PreparedStatement ps = DBConnection.getconnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int month = rs.getInt("month(start)");
                String type = rs.getString("type");
                int total = rs.getInt("Total");

                Reports R = new Reports(month, type, total);
                reports.add(R);
            }

        } catch (SQLException ex) {
        }
        return reports;
    }
}
