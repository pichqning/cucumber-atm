package ku.atm;

/**
   An ATM that accesses a bank.
 */
public class ATM {
	private int state;
	private int customerNumber;
	private Customer currentCustomer;
	private BankAccount currentAccount;
	private Bank theBank;

	public static final int START = 1;
	public static final int TRANSACT = 2;

	/**
      Constructs an ATM for a given bank.
      @param aBank the bank to which this ATM connects
	 */    
	public ATM(Bank aBank) {
		theBank = aBank;
		customerNumber = -1;
		currentAccount = null;
		state = START; 
	}

	/**
      Resets the ATM to the initial state.
	 */
	public void reset() {
		customerNumber = -1;
		currentAccount = null;
		state = START;             
	}

	/** 
      Finds customer in bank.
      If found sets state to ACCOUNT, else to START.
      (Precondition: state is PIN)
      @param pin the PIN of the current customer
	 */
	public boolean validateCustomer(int customerNum, int pin) {
		if (state == START) {

            if (theBank.findCustomer(customerNum) != null &&
                    theBank.findCustomer(customerNum).match(pin)) {
                customerNumber = customerNum;
                currentCustomer = theBank.findCustomer(customerNumber);
                currentAccount = currentCustomer.getAccount();
                state = TRANSACT;
                return true;
            }
            return false;
        }
        return false;
	}

	/** 
      Withdraws amount from current account. 
      (Precondition: state is TRANSACT)
      @param value the amount to withdraw
	 */
	public void withdraw(double value) throws NotEnoughBalanceException {
		if (state == TRANSACT) {
            currentAccount.withdraw(value);
        }
	}

	public void overdraw(double value, boolean isAccountOD) throws NotEnoughBalanceException {
		if (state == TRANSACT) {
			currentAccount.overdraw(value, isAccountOD);
		}
	}

	/** 
      Deposits amount to current account. 
      (Precondition: state is TRANSACT)
      @param value the amount to deposit
	 */
	public void deposit(double value) {
        if (state == TRANSACT) {
            currentAccount.deposit(value);
        }
	}

	/** 
      Gets the balance of the current account. 
      (Precondition: state is TRANSACT)
      @return the balance
	 */
	public double getBalance() {
        if (state == TRANSACT) {
            return currentAccount.getBalance();
        }
        return -1;
	}

	public double getNegotiatedAmount() {
		if (state == TRANSACT) {
			return currentAccount.getNegotiatedAmount();
		}
		return -1;
	}

	public void transfer(int customerNum, double amount) throws NotEnoughBalanceException {
        if (state == TRANSACT) {
            Customer receivingCustomer = theBank.findCustomer(customerNum);
            BankAccount receivingAccount = receivingCustomer.getAccount();
            currentAccount.withdraw(amount);
            receivingAccount.deposit(amount);
        }
	}

	/**
      Gets the current state of this ATM.
      @return the current state
	 */
	public int getState() {
		return state;
	}

	public boolean setIsOD(String status) {
		if(status.equals("OD")) return true;
		else return false;
	}

}
