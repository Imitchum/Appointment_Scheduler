package Model;


/**
 * This is the contacts model class
 */
public class Contacts {
    public int total;
    public int contactID;
    public String contactName;


    /**
     * Contacts constructor
     * @param contactID - contact ID
     * @param contactName - contact name
     */
    public Contacts(int contactID, String contactName) {
        this.contactID = contactID;
        this.contactName = contactName;

    }

    /**
     * Contacts constructor for contact ID
     * @param contactID - contact ID
     */
    public Contacts(int contactID) {
    }

    /**
     * Contacts constructor for contact ID and total
     * @param contactID - contact ID
     * @param total - total
     */
    public Contacts(int contactID, int total) {
        this.contactID = contactID;
        this.total = total;
    }


    /**
     * Method to get string value of contact name
     * @return - contact name
     */
    @Override
    public String toString(){
        return contactName;
    }

    /**
     * Method to get contact ID
     * @return - contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * This method gets the total for the contact report
     * @return - total
     */
    public int getTotal() {
        return total;
    }

    /**
     * This method sets the total
     * @param total - total
     */
    public void setTotal(int total) {
        this.total = total;
    }

}
