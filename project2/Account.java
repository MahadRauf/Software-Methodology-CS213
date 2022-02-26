package project2;

public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;


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
    }



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

    @Override
    public String toString() {
        //profile + balance
        String AccountString = holder.toString() + "::" + "Balance $" + String.format("%,.2f", balance);
        return AccountString;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
    public abstract double monthlyInterest(); //return the monthly interest
    public abstract double fee(); //return the monthly fee
    public abstract String getType(); //return the account type (class name)
}
