package Model;

public class Contacts {

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int total;
    public int contactID;
    public String contactName;


    public Contacts(int contactID, String contactName) {
        this.contactID = contactID;
        this.contactName = contactName;

    }

    public Contacts(int contactID) {
    }

    public Contacts(int contactID, int total) {
        this.contactID = contactID;
        this.total = total;
    }

    @Override
    public String toString(){
        return contactName;
    }



    public Contacts(String contactName) {
    }

    public int getContactID() {
        return contactID;
    }

    public String getContactName() {
        return contactName;
    }




}
