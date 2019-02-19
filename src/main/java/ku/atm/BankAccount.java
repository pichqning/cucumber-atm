package ku.atm;

/**
   A bank account has a balance that can be changed by 
   deposits and withdrawals.
*/
public class BankAccount {
   private double balance;
   private double negotiatedAmount;

   /**
      Constructs a bank account with a zero balance.
   */
   public BankAccount() {
      balance = 0;
   }

   /**
      Constructs a bank account with a given balance.
      @param initialBalance the initial balance
   */
   public BankAccount(double initialBalance) {
      balance = initialBalance;
   }

   public BankAccount(double initialBalance, double negotiatedAmount){
      this.balance = initialBalance;
      this.negotiatedAmount = negotiatedAmount;
   }
 
   /** 
      Deposits money into the account.
      @param amount the amount of money to withdraw
   */
   public void deposit(double amount) {
      balance = balance + amount;
   }

   /** 
      Withdraws money from the account.
      @param amount the amount of money to deposit
   */
   public void withdraw(double amount) throws NotEnoughBalanceException {
       if (amount > balance)
         throw new NotEnoughBalanceException("cannot withdraw more than balance");
       balance = balance - amount;
   }

   public void overdraw(double amount, boolean isAccountOD) throws NotEnoughBalanceException {
      if (amount > negotiatedAmount || !isAccountOD)
         throw new NotEnoughBalanceException("cannot overdraw more than negotiated amount");
      else negotiatedAmount -= amount;
   }

   /** 
      Gets the account balance.
      @return the account balance
   */
   public double getBalance() {
      return balance; 
   }

   public double getNegotiatedAmount() {
      return negotiatedAmount;
   }
}

