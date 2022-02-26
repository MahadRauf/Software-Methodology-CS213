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
        Account[] newAccArray = new Account[this.accounts.length];
        for (int i = 0; i < delIndex; i++) {
            newAccArray[i] = this.accounts[i];
        }
        for (int i = delIndex; i < this.accounts.length - 1; i++) {
            newAccArray[i] = this.accounts[i + 1];
        }
        this.numAccts--;
        this.accounts = newAccArray;
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
}
