package ku.atm;

import java.util.HashMap;
import java.util.Map;

/**
   A bank contains customers with bank accounts.
*/
public class Bank {
   /**
      Constructs a bank with no customers.
   */
   public Bank() {
      customers = new HashMap<Integer, Customer>();
   }

   /**
      Adds a customer to the bank.
      @param c the customer to add
   */
   public void addCustomer(Customer c) {
      customers.put(c.getCustomerNumber(), c);
   }
   
   /** 
      Finds a customer in the bank.
      @param aNumber a customer number
      @return the matching customer, or null if no customer
      matches
   */
   public Customer findCustomer(int aNumber) {
	  return customers.get(aNumber);
   }

   public boolean isODCustomer(int aNumber) {
      Customer c = findCustomer(aNumber);
      return c.getIsOD();
   }
   
   private Map<Integer, Customer> customers;
}


