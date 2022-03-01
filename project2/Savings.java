package project2;

/**
 * Extends the abstract class Account to represent a Savings Account.
 * @author Mahad Rauf, Moeez Shahid
 */
public class Savings extends Account{
    /** loyal code of the account, 0: nonloyal, 1: loyal */
    protected int loyalty;
    private static final double annualInterest = 0.003;
    private static final double monthlyInterest = annualInterest/Account.monthsInYear;
    private static final double loyalAnnualInterest = 0.0045;
    private static final double loyalMonthlyInterest = loyalAnnualInterest/Account.monthsInYear;
    private static final double fee = 6.00;
    private static final double feeWaived = 300.00;
    private static final double noFee = 0.00;
    private static final int loyal = 1;

    /**
     * Constructor for Savings which utilizes the single argument constructor defined in its superclass, Account.
     * @param profile holder of the account
     * @param balance initial deposit for the account
     */
    public Savings(Profile profile, double balance) {
        super(profile, balance);
    }

    /**
     * Constructor for type Savings which utilizes the constructor of its superclass, Account. Also sets loyalty on its own
     * @param profile holder of the account
     * @param balance initial deposit into the account
     * @param loyalty loyalty code for the account
     */
    public Savings(Profile profile, double balance, int loyalty) {
        super(profile, balance);
        this.loyalty = loyalty;

    }

    /**
     * returns the monthly interest rate for the account.
     * @return the monthly interest rate for the account.
     */
    @Override
    public double monthlyInterest() {
        if(loyalty == loyal) {
            return loyalMonthlyInterest;
        }
        return monthlyInterest;
    }

    /**
     * returns the fee for the account type
     * @return the fee for the account type
     */
    @Override
    public double fee() {
        if(balance >= feeWaived) {
            return noFee;
        }
        return fee;
    }

    /**
     * Returns the type of the account as a string (name of its class: Money Market)
     * @return "Money Market" as that is the name of the class.
     */
    @Override
    public String getType() {
        String accountType = "Savings";
        return accountType;
    }
    /**
     * checks whether the account is loyal
     * @return "::Loyal" if loyal, "" otherwise
     */
    public String isLoyal() {
        if(loyalty == loyal) {
            return "::Loyal";
        }
        return "";
    }

    /**
     * Overrides the toString() method
     * @return string representation of the Savings account.
     */
    @Override
    public String toString() {
        String AccountString;
        if(closed) {
            AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + isLoyal() + "::" +"CLOSED";
        }
        else if(loyalty == loyal) {
            AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + isLoyal();
        }
        else {
            AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance);
        }

        return AccountString;
    }
}