package project2;

/**
 * Abstract class representing a bank account that defines some of its functionalities.
 * @author Mahad Rauf, Moeez Shahid
 */
public abstract class Account {
    /** owner of the account */
    protected Profile holder;
    /** whether the account is closed or not. true = closed, false = open. */
    protected boolean closed;
    /** current balance of the account */
    protected double balance;

    protected static final double monthsInYear = 12;

    /**
     * Parameterized constructor that takes a 'Profile' object and creates an instance of an 'Account', setting other instance variables to default values.
     * @param profile a 'profile' instance which holds the profile, account status, and balance of a user.
     */
    public Account(Profile profile) {
        holder = profile;
        closed = false;
        balance = 0;
    }

    /**
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     * @param balance the balance that the account will have.
     */
    public Account(Profile profile, double balance) {
        holder = profile;
        this.balance = balance;
        this.closed = false;
    }


    /**
     * Overrides the equals(Object obj) method from Object class. Compares accounts on whether their types and holders are equal.
     * @param obj Object to compare the account with.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Account){
            Account accObj = (Account) obj;
            int typeCmpr = this.getType().compareTo(accObj.getType());
            if(typeCmpr == 0 && this.holder.equals(accObj.holder)){
                return true;
            }
        }
        return false;
    }

    /**
     * Overrides the toString() method with one appropriate for Account type.
     * @return string representation of the Account.
     */
    @Override
    public String toString() {
        //profile + balance
        String AccountString = holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance);
        return AccountString;
    }

    /**
     * Withdraws the parameter amount from the account.
     * @param amount amount to withdraw.
     */
    public void withdraw(double amount) {
        balance -= amount;
    }

    /**
     * Deposits the parameter amount into the account.
     * @param amount amount to deposit.
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Returns the monthly interest rate for the account
     * @return the monthly interest rate
     */
    public abstract double monthlyInterest();

    /**
     * Returns the monthly fee for the account
     * @return the monthly fee
     */
    public abstract double fee();

    /**
     * Returns the type of the account as a string (name of its class)
     * @return string that is the type of the account
     */
    public abstract String getType();
}