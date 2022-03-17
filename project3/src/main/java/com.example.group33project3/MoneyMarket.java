package com.example.group33project3;

/**
 * Extends the class Savings to represent a Money Market Account.
 * @author Mahad Rauf, Moeez Shahid
 */
public class MoneyMarket extends Savings {
    /** number of times withdrawals were made from the account */
    protected int withdraws = 0;
    private static final double annualInterest = 0.008;
    private static final double monthlyInterest = annualInterest/Account.monthsInYear;
    private static final double loyalAnnualInterest = 0.0095;
    private static final double loyalMonthlyInterest = loyalAnnualInterest/Account.monthsInYear;
    private static final double fee = 10.00;
    private static final double feeWaived = 2500.00;
    private static final double noFee = 0.00;
    private static final int nonLoyal = 0;
    private static final int loyal = 1;
    private static final int withdrawLoyalLimit = 3;

    /**
     * Constructor for type MoneyMarket which utilizes the constructor of its superclass, Savings. Also sets loyalty on its own.
     * @param profile holder of the account
     * @param balance initial deposit for the account
     */
    public MoneyMarket(Profile profile, double balance) {
        super(profile, balance);
        loyalty = loyal;
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
     * Withdraws the parameter amount from the account. If the amount of withdrawals exceeds the maximum number allowed for loyal
     * accounts, sets the account as nonloyal.
     * @param amount amount to withdraw.
     */
    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        this.withdraws++;
        if((withdraws > withdrawLoyalLimit)||(balance < feeWaived)){
            loyalty = nonLoyal;
        }
    }

    /**
     * Returns the type of the account as a string (name of its class: Money Market)
     * @return "Money Market" as that is the name of the class.
     */
    @Override
    public String getType() {
        String accountType = "Money Market";
        return accountType;
    }

    /**
     * Overrides the toString() method
     * @return string representation of the Money Market account.
     */
    @Override
    public String toString() {
        String AccountString;
        if(closed) {
            AccountString = getType() + " Savings::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + "CLOSED" +"::withdrawl: " + withdraws;
        }
        else if(loyalty == loyal) {
            AccountString = getType() + " Savings::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + super.isLoyal() + "::withdrawl: " + withdraws;
        }
        else {
            AccountString = getType() + " Savings::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::withdrawl: " + withdraws;
        }

        return AccountString;
    }
}