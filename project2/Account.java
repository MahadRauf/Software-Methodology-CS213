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
     * Second parameterized constructor that takes a 'Profile' instance and a number of credits and uses them to construct a 'Student' instance, setting other instance variables to default values.
     * @param profile a 'Profile' instance which holds the name and major of a given student.
     * @param credits the number of credits the student is taking this semester.
     */
    public Account(Profile profile, double balance) {
        holder = profile;
        if(balance >=0) {
        	this.balance = balance;
        }
        else {
        	//throw exception
        }
        closed = false;
    }
	
	
	
	@Override
	public boolean equals(Object obj) { 
		if (holder.equals(((Account)obj).holder)) {
			return true;
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
		if(amount > balance) {
			//throw exception error
		}
		balance -= amount;
	}
	
	public void deposit(double amount) {
		if(amount <= 0) {
			//throw exception
		}
		balance += amount;
	}
	public abstract double monthlyInterest(); //return the monthly interest
	public abstract double fee(); //return the monthly fee
	public abstract String getType(); //return the account type (class name)
}
