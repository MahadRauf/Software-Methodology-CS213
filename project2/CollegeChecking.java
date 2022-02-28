package project2;

public class CollegeChecking extends Checking{
    protected int schoolCode;
    private static final double monthlyInterest = 0.25/12;
    private static final double fee = 0.00;
    private static final int NewBrunswick = 0;
    private static final int Newark = 1;
    private static final int Camden = 2;

    public CollegeChecking(Profile profile, double balance, int schoolCod) {
        super(profile, balance);
        schoolCode = schoolCod;

    }
    public double monthlyInterest() {
        return monthlyInterest;
    }

    public double fee() {
        return fee;
    }

    public String getType() {
        String accountType = "College Checking";
        return accountType;
    }

    private String getCampus(int schoolCode) {
        if(schoolCode == NewBrunswick) {
            return "NEW_BRUNSWICK";
        }
        if(schoolCode == Newark) {
            return "NEWARK";
        }
        return "CAMDEN";
    }

    @Override
    public String toString() {
        //profile + balance
    	String AccountString;
    	if(closed == true) {
    		AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + "CLOSED" + "::" + getCampus(schoolCode);
            return AccountString;
    	}
        AccountString = getType() + "::" + holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance) + "::" + getCampus(schoolCode);
        return AccountString;
    }

}
