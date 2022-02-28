package project2;

public class Checking extends Account{

    private static final double monthlyInterest = 0.01/12;
    private static final double fee = 25.00;
    private static final double feeWaived = 1000.00;
    private static final double noFee = 0.00;

    public Checking(Profile profile) {
        super(profile);
    }

    public Checking(Profile profile, double balance) {
        super(profile, balance);
    }

    public double monthlyInterest() {
        return monthlyInterest;
    }

    public double fee() {
        if(balance >= feeWaived) {
            return noFee;
        }
        return fee;
    }

    public String getType() {
        String accountType = "Checking";
        return accountType;
    }

    @Override
    public String toString() {
        //profile + balance
    	String AccountString;
    	if(closed == true) {
        AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + "CLOSED";
        return AccountString;
    	}
    	AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance);
        return AccountString;
    }

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
