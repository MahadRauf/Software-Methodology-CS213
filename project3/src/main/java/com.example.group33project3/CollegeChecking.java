package com.example.group33project3;
/**
 * Extends the class Checking to represent a College Checking Account.
 * @author Mahad Rauf, Moeez Shahid
 */
public class CollegeChecking extends Checking{
    /** code denoting which campus student belongs to. 0: New Brunswick, 1: Newark, 2: Camden */
    protected int schoolCode;
    private static final double annualInterest = 0.0025;
    private static final double monthlyInterest = annualInterest/Account.monthsInYear;
    private static final double fee = 0.00;
    private static final int NewBrunswick = 0;
    private static final int Newark = 1;
    private static final int Camden = 2;

    /**
     * Constructor for type CollegeChecking which utilizes the constructor of its superclass, Checking. Also sets schoolCode on its own.
     * @param profile holder of the account
     * @param balance initial deposit for the account
     * @param schoolCod campus affiliation
     */
    public CollegeChecking(Profile profile, double balance, int schoolCod) {
        super(profile, balance);
        schoolCode = schoolCod;
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
     * returns the fee for the account type
     * @return fee for the account
     */
    @Override
    public double fee() {
        return fee;
    }

    /**
     * Returns the type of the account as a string (name of its class: College Checking)
     * @return "College Checking" as that is the name of the class.
     */
    @Override
    public String getType() {
        String accountType = "College Checking";
        return accountType;
    }

    /**
     * returns the name of the campus that the campus code corresponds to.
     * @param schoolCode code that corresponds to a campus, 0: New Brunswick, 1: Newark, 2: Camden
     * @return name of the campus that the campus code corresponds to.
     */
    private String getCampus(int schoolCode) {
        if(schoolCode == NewBrunswick) {
            return "NEW_BRUNSWICK";
        }
        if(schoolCode == Newark) {
            return "NEWARK";
        }
        return "CAMDEN";
    }

    /**
     * Overrides the toString() method.
     * @return string representation of the College Checking account
     */
    @Override
    public String toString() {
        //profile + balance
        String AccountString;
        if(closed) {
            AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + "CLOSED" + "::" + getCampus(schoolCode);
            return AccountString;
        }
        AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + getCampus(schoolCode);
        return AccountString;
    }

}