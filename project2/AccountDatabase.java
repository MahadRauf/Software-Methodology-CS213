package project2;

public class AccountDatabase {
    private Account [] accounts;
    private int numAccts;
    private static final int INITIAL_SIZE = 4;
    private static final int ARRAY_GROWTH = 4;
    private static final int INITIAL_ACCTS = 0;
    private static final int NOT_FOUND = -1;


    public AccountDatabase(){
        this.accounts = new Account[INITIAL_SIZE];
        this.numAccts = INITIAL_ACCTS;
    }

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

    private void grow() {
        int newSize = this.accounts.length + ARRAY_GROWTH;
        Account[] newAccArray = new Account[newSize];
        for (int i = 0; i < numAccts; i++) {
            newAccArray[i] = this.accounts[i];
        }
        this.accounts = newAccArray;
    }

    public boolean open(Account account) {
        if(account == null){
            return false;
        }
        if (find(account) != NOT_FOUND) {
            return false;
        }
        if (this.numAccts == this.accounts.length) {
            this.grow();
        }
        this.accounts[this.numAccts] = account;
        this.numAccts++;
        return true;
    }

    public boolean close(Account account) {
        if(account == null){
            return false;
        }
        int delIndex = find(account);
        if (delIndex == NOT_FOUND) {
            return false;
        }
        this.accounts[delIndex].closed = true;
        return true;
    }

    public void deposit(Account account){
        if(account == null){
            return;
        }
        int acctIndex = find(account);
        if(acctIndex == NOT_FOUND){
            return;
        }
        this.accounts[acctIndex].balance += account.balance;
    }

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
        this.accounts[acctIndex].balance -= account.balance;
        if(this.accounts[acctIndex] instanceof MoneyMarket){
            ((MoneyMarket) this.accounts[acctIndex]).withdraws++;
        }
        return true;
    }

    public void print(){
        System.out.println();
        System.out.println("*list of accounts in the database*");
        for (int i = 0; i < this.numAccts; i++) {
            System.out.println(this.accounts[i].toString());
        }
        System.out.println("*end of list*");
        System.out.println();
    }

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
                    }else{
                        if(minProfile.getFname().compareTo(jProfile.getFname()) > 0){
                            minIdx = j;
                            minType = jType;
                        }else{
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

    public void printByAccountType() {
        this.sortByAccountType();
        System.out.println();
        System.out.println("*list of accounts by account type*");
        for (int i = 0; i < this.numAccts; i++) {
            System.out.println(this.accounts[i].toString());
        }
        System.out.println("*end of list*");
        System.out.println();
    }

    public void printFeeAndInterest(){
        System.out.println();
        System.out.println("*list of accounts with fee and monthly interest*");
        for (int i = 0; i < this.numAccts; i++) {
            double fee = this.accounts[i].fee();
            double interest = this.accounts[i].monthlyInterest();
            System.out.println(this.accounts[i].toString() + "::fee $" + String.format("%,.2f", fee) + "::monthly interest $"
                    + String.format("%,.2f", interest));
        }
        System.out.println("*end of list*");
        System.out.println();
    }

}
