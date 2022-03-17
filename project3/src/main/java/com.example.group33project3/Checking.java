package com.example.group33project3;

/**
 * Extends the abstract class Account to represent a Checking Account.
 * @author Mahad Rauf, Moeez Shahid
 */
public class Checking extends Account{

    private static final double annualInterest = 0.001;
    private static final double monthlyInterest = annualInterest/Account.monthsInYear;
    private static final double fee = 25.00;
    private static final double feeWaived = 1000.00;
    private static final double noFee = 0.00;

    /**
     * Constructor for Checking which utilizes the single argument constructor defined in its superclass, Account.
     * @param profile the holder of the account
     */
    public Checking(Profile profile) {
        super(profile);
    }

    /**
     * Constructor for Checking which utilizes the two argument constructor defined in its superclass, Account.
     * @param profile the holder of the account
     * @param balance the initial deposit for the account
     */
    public Checking(Profile profile, double balance) {
        super(profile, balance);
    }

    /**
     * returns the monthly interest rate for the account.
     * @return the monthly interest rate
     */
    @Override
    public double monthlyInterest() {
        return monthlyInterest;
    }

    /**
     * returns the fee applied to the account
     * @return fee for the account
     */
    @Override
    public double fee() {
        if(balance >= feeWaived) {
            return noFee;
        }
        return fee;
    }

    /**
     * Returns the type of the account as a string (name of its class: Checking)
     * @return "Checking" as that is the name of the class.
     */
    @Override
    public String getType() {
        String accountType = "Checking";
        return accountType;
    }

    /**
     * Overrides the toString() method.
     * @return string representation of the Checking account
     */
    @Override
    public String toString() {
        String AccountString;
        if(closed) {
            AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + "CLOSED";
            return AccountString;
        }
        AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance);
        return AccountString;
    }

    /**
     * Checks two accounts whether they are similar checking accounts. Meaning that they have the same holder.
     * @param acct account to compare to.
     * @return true if the two instances of checking accounts are similar, false otherwise.
     */
    public boolean isSimilar(Account acct){
        if(acct instanceof Checking){
            int lnameComp = this.holder.getLname().toLowerCase().compareTo(acct.holder.getLname().toLowerCase());
            int fnameComp = this.holder.getFname().toLowerCase().compareTo(acct.holder.getFname().toLowerCase());
            int dobComp = this.holder.getDOB().compareTo(acct.holder.getDOB());
            if(lnameComp == 0 && fnameComp == 0 && dobComp == 0){
                return true;
            }
        }else{
            return false;
        }
        return false;
    }


}