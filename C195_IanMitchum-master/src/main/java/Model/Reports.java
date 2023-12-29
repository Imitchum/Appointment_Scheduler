package Model;

/**
 * This is the Reports model class
 */
public class Reports {
    int month;
    String type;
    int total;


    /**
     * This is the Reports constructor
     * @param month - month
     * @param type - type
     * @param total - total
     */
    public Reports(int month, String type, int total) {
        this.month = month;
        this.type = type;
        this.total = total;
    }

    /**
     * This method gets the month
     * @return - month
     */
    public int getMonth() {
        return month;
    }

    /**
     * This method sets the month
     * @param month - month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * This method gets the type
     * @return - type
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets the type
     * @param type - type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method gets the total
     * @return - total
     */
    public int getTotal() {
        return total;
    }

    /**
     * This method sets the total using count integer
     * @param count - count
     */
    public void setTotal(int count) {
        this.total = total;
    }
}