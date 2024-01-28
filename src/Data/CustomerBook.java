
package Data;

/**
 *
 * @author raaz4
 */
public class CustomerBook {
    int customerID ;
    public CustomerBook(){
        this.customerID = 0;
    }
    public CustomerBook(int custID){
        this.customerID = custID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "CustomerBook{" + "customerID=" + customerID + '}';
    }
}
