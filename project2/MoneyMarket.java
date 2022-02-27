package project2;

public class MoneyMarket extends Savings {
    protected int withdraws = 0; //increment up every time you withdraw
    private static final double monthlyInterest = 0.8;
    private static final double loyalMonthlyInterest = 0.95;
    private static final double fee = 10.00;
    private static final double feeWaived = 2500.00;
    private static final double noFee = 0.00;
    private static final int nonLoyal = 0;
    private static final int loyal = 1;
    private static final int withdrawLoyalLimit = 3;

    public MoneyMarket(Profile profile, double balance) {
        super(profile, balance);
        loyalty = loyal;

    }
    public double monthlyInterest() {
        if((withdraws > withdrawLoyalLimit) || (balance < feeWaived)) {
            loyalty = nonLoyal;
        }
        if(loyalty == loyal) {
            return loyalMonthlyInterest;
        }
        return monthlyInterest;
    }

    public double fee() {
        if(balance >= feeWaived) {
            return noFee;
        }
        return fee;
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        this.withdraws++;
    }

    public String getType() {
        String accountType = "Money Market";
        return accountType;
    }

    public String isLoyal() {
        if(loyalty == loyal) {
            return "::Loyal";
        }
        return null;
    }
    @Override
    public String toString() {
        //profile + balance
        String AccountString;
        if((withdraws > withdrawLoyalLimit)||(balance < feeWaived)){
            loyalty = nonLoyal;
        }
        if(loyalty == loyal) {
            AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + isLoyal() + ":withdrawl: " + withdraws;
        }
        else {
            AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance);
        }

        return AccountString;
    }
}
