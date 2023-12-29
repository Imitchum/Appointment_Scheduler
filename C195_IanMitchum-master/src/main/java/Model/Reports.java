package Model;

public class Reports {

    int month;
    String type;
    int total;

    public Reports(int month, String type, int total) {
        this.month = month;
        this.type = type;
        this.total = total;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int count) {
        this.total = total;
    }
}