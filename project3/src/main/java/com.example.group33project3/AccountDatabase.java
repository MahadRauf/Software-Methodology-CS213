package com.example.group33project3;

/**
 * Implementation of an account database as an array of Account objects that grows as the number of accounts increase.
 * @author Mahad Rauf, Moeez Shahid
 */
public class AccountDatabase {
    /** array where accounts are stored*/
    private Account [] accounts;
    /** number of accounts in the array */
    private int numAccts;
    private static final int INITIAL_SIZE = 4;
    private static final int ARRAY_GROWTH = 4;
    private static final int INITIAL_ACCTS = 0;
    protected static final int NOT_FOUND = -1;
    private static final double CLOSED_BAL = 0;

    /**
     * Constructor for AccountDatabase class. Creates an Account array of initial size 4 and sets the number of accounts to 0.
     */
    public AccountDatabase(){
        this.accounts = new Account[INITIAL_SIZE];
        this.numAccts = INITIAL_ACCTS;
    }

    /**
     * Uses the find(Account account) method to find an account in the array that is the same as the parameter account and
     * returns it.
     * @param acct account which you want to find (the same holder and type as)
     * @return the account that is the same as the parameter account if it is found, null otherwise.
     */
    public Account getAcct(Account acct){
        int retIdx = this.find(acct);
        if(retIdx == NOT_FOUND){
            return null;
        }
        Account retAcct = this.accounts[retIdx];
        return retAcct;
    }

    /**
     * Searches the account array for a Checking/College Checking account that has the same holder as another Checking/College Checking
     * account.
     * @param account account which you want a similar Checking/College Checking account
     * @return the account that is similar to the parameter account if it exists, null if it doesn't or the parameter isn't an instance of Checking.
     */
    public Account findSimilarChecking(Account account){
        for(int i = 0; i < numAccts; i++){
            if(this.accounts[i] instanceof Checking && ((Checking) this.accounts[i]).isSimilar(account)){
                return this.accounts[i];
            }
        }
        return null;
    }

    /**
     * Searches the account array for an account that is the same as the parameter account and returns its index in the array.
     * @param account account which you want to find in the array.
     * @return index of the account in the array if it is present, -1 (not found) otherwise.
     */
    private int find(Account account){
        if(account == null){
            return NOT_FOUND;
        }
        for (int i = 0; i < numAccts; i++) {
            if (this.accounts[i].equals(account)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * grows the account array by 4.
     */
    private void grow() {
        int newSize = this.accounts.length + ARRAY_GROWTH;
        Account[] newAccArray = new Account[newSize];
        for (int i = 0; i < numAccts; i++) {
            newAccArray[i] = this.accounts[i];
        }
        this.accounts = newAccArray;
    }

    /**
     * Adds a new account to the account array. Will not add if the account is already in the array.
     * @param account account to add
     * @return true of the account was successfully added, false otherwise.
     */
    public boolean open(Account account) {
        if(account == null){
            return false;
        }
        int acctIdx = find(account);
        if (acctIdx != NOT_FOUND) {
            if(this.accounts[acctIdx].closed){
                this.accounts[acctIdx].closed = false;
                this.accounts[acctIdx].deposit(account.balance);
                if(this.accounts[acctIdx] instanceof MoneyMarket && account instanceof MoneyMarket){
                    ((MoneyMarket) this.accounts[acctIdx]).loyalty = ((MoneyMarket) account).loyalty;
                }
                if(this.accounts[acctIdx] instanceof Savings && account instanceof Savings){
                    ((Savings) this.accounts[acctIdx]).loyalty = ((Savings) account).loyalty;
                }
                return true;
            }else{
                return false;
            }
        }
        if (this.numAccts == this.accounts.length) {
            this.grow();
        }
        this.accounts[this.numAccts] = account;
        this.numAccts++;
        return true;
    }

    /**
     * Closes an account that is in the array.
     * @param account account to close
     * @return true if account was successfully deleted, false otherwise.
     */
    public boolean close(Account account) {
        if(account == null){
            return false;
        }
        int delIndex = find(account);
        if (delIndex == NOT_FOUND) {
            return false;
        }
        this.accounts[delIndex].closed = true;
        this.accounts[delIndex].balance = CLOSED_BAL;
        if(this.accounts[delIndex] instanceof Savings){
            ((Savings) this.accounts[delIndex]).loyalty = 0;
            if(this.accounts[delIndex] instanceof MoneyMarket){
                ((MoneyMarket)this.accounts[delIndex]).withdraws = 0;
            }
        }
        return true;
    }

    /**
     * Finds an account in the array that is the same as the parameter account and deposits the parameter account's balance into that account.
     * @param account account to find a similar in the array to deposit into
     */
    public void deposit(Account account){
        if(account == null){
            return;
        }
        int acctIndex = find(account);
        if(acctIndex == NOT_FOUND){
            return;
        }
        this.accounts[acctIndex].deposit(account.balance);
    }

    /**
     * Finds an account in the array that is the same as the parameter account and withdraws the parameter account's balance from that account.
     * @param account account to find a similar in the array to withdraw from
     * @return true if the amount was successfully withdrawn, false otherwise.
     */
    public boolean withdraw(Account account){
        if(account == null){
            return false;
        }
        int acctIndex = find(account);
        if(acctIndex == NOT_FOUND){
            return false;
        }
        if(this.accounts[acctIndex].balance < account.balance){
            return false;
        }
        this.accounts[acctIndex].withdraw(account.balance);
        return true;
    }

    /**
     * Prints out all the accounts currently in the account array.
     * UPDATE JAVADOC COMMENTS FOR ALL PRINT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public String print(){
        String ret = "";
        if(numAccts == 0){
            ret = "Account Database is empty!\n";
            return ret;
        }
        ret = ret + "\n" + "*list of accounts in the database*\n";
        for (int i = 0; i < this.numAccts; i++) {
            ret = ret + this.accounts[i].toString() + "\n";
        }
        ret = ret + "*end of list*\n" + "\n";
        return ret;
    }

    /**
     * sorts the accounts in the array by type and holder, in that order of priority.
     */
    private void sortByAccountType(){
        for(int i = 0; i < this.numAccts - 1; i++){
            int minIdx = i;
            String minType = this.accounts[i].getType();
            for(int j = i + 1; j < numAccts; j++){
                String jType = this.accounts[j].getType();
                if(minType.compareTo(jType) > 0){
                    minIdx = j;
                    minType = jType;
                }else if(minType.compareTo(jType) == 0){
                    Profile minProfile = this.accounts[minIdx].holder;
                    Profile jProfile = this.accounts[j].holder;
                    if(minProfile.getLname().compareTo(jProfile.getLname()) > 0){
                        minIdx = j;
                        minType = jType;
                    }else if(minProfile.getLname().compareTo(jProfile.getLname()) == 0){
                        if(minProfile.getFname().compareTo(jProfile.getFname()) > 0){
                            minIdx = j;
                            minType = jType;
                        }else if(minProfile.getFname().compareTo(jProfile.getFname()) == 0){
                            if(minProfile.getDOB().compareTo(jProfile.getDOB()) == 1){
                                minIdx = j;
                                minType = jType;
                            }
                        }
                    }
                }
            }
            Account temp = this.accounts[minIdx];
            this.accounts[minIdx] = this.accounts[i];
            this.accounts[i] = temp;
        }
    }

    /**
     * Prints all the accounts in the array by type and holder, in that order of priority.
     */
    public String printByAccountType() {
        String ret = "";
        if(numAccts == 0){
            ret = ret + "Account Database is empty!\n";
            return ret;
        }
        this.sortByAccountType();
        ret = ret + "\n" + "*list of accounts by account type.*\n";
        for (int i = 0; i < this.numAccts; i++) {
            ret = ret + this.accounts[i].toString() + "\n";
        }
        ret = ret + "*end of list*\n" + "\n";
        return ret;
    }

    /**
     * Prints all the accounts in the account array along with the fees and interest that will be applied to their account.
     */
    public String printFeeAndInterest(){
        String ret = "";
        if(numAccts == 0){
            ret = ret + "Account Database is empty!\n";
            return ret;
        }
        ret = ret + "\n" + "*list of accounts with fee and monthly interest\n";
        for (int i = 0; i < this.numAccts; i++) {
            double fee = this.accounts[i].fee();
            double interest = this.accounts[i].monthlyInterest();
            double interestAdded = interest * this.accounts[i].balance;
            ret = ret + this.accounts[i].toString() + "::fee $" + String.format("%,.2f", fee) + "::monthly interest $"
                    + String.format("%,.2f", interestAdded) + "\n";
        }
        ret = ret + "*end of list.\n" + "\n";
        return ret;
    }

    /**
     * Prints all the accounts in the account array after fees and interest have been applied to the accounts.
     */
    public String updateAndPrint(){
        String ret = "";
        if(numAccts == 0){
            ret = ret + "Account Database is empty!\n";
            return ret;
        }
        // fees and interest and print within same loop
        ret = ret + "\n" + "*list of accounts with updated balance\n";
        for (int i = 0; i < this.numAccts; i++) {
            double fee = this.accounts[i].fee();
            double interest = this.accounts[i].monthlyInterest();
            this.accounts[i].balance -= fee;
            this.accounts[i].balance += this.accounts[i].balance * interest;
            ret = ret + this.accounts[i].toString() + "\n";
        }
        ret = ret + "*end of list.\n" + "\n";
        return ret;
    }
}
