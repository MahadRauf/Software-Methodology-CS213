package project2;

public class AccountDatabase {
    private Account [] accounts;
    private int numAccts;
    private static final int INITIAL_SIZE = 4;
    private static final int ARRAY_GROWTH = 4;
    private static final int INITIAL_ACCTS = 0;
    protected static final int NOT_FOUND = -1;
    private static final double CLOSED_BAL = 0;


    public AccountDatabase(){
        this.accounts = new Account[INITIAL_SIZE];
        this.numAccts = INITIAL_ACCTS;
    }

    public Account getAcct(Account acct){
        int retIdx = this.find(acct);
        if(retIdx == NOT_FOUND){
            return null;
        }
        Account retAcct = this.accounts[retIdx];
        return retAcct;
    }

    public Account findSimilarChecking(Account account){
        for(int i = 0; i < numAccts; i++){
            if(this.accounts[i] instanceof Checking && ((Checking) this.accounts[i]).isSimilar(account)){
                return this.accounts[i];
            }
        }
        return null;
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

    public void print(){
        if(numAccts == 0){
            System.out.println("Account Database is empty!");
            return;
        }
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

    public void printByAccountType() {
        if(numAccts == 0){
            System.out.println("Account Database is empty!");
            return;
        }
        this.sortByAccountType();
        System.out.println();
        System.out.println("*list of accounts by account type.*");
        for (int i = 0; i < this.numAccts; i++) {
            System.out.println(this.accounts[i].toString());
        }
        System.out.println("*end of list*");
        System.out.println();
    }

    public void printFeeAndInterest(){
        if(numAccts == 0){
            System.out.println("Account Database is empty!");
            return;
        }
        System.out.println();
        System.out.println("*list of accounts with fee and monthly interest.*");
        for (int i = 0; i < this.numAccts; i++) {
            double fee = this.accounts[i].fee();
            double interest = this.accounts[i].monthlyInterest();
            System.out.println(this.accounts[i].toString() + "::fee $" + String.format("%,.2f", fee) + "::monthly interest $"
                    + String.format("%,.2f", interest));
        }
        System.out.println("*end of list*");
        System.out.println();
    }

    public void updateAndPrint(){
        if(numAccts == 0){
            System.out.println("Account Database is empty!");
            return;
        }
        // fees and interest and print within same loop
        System.out.println();
        System.out.println("*list of accounts with updated balance.*");
        for (int i = 0; i < this.numAccts; i++) {
            double fee = this.accounts[i].fee();
            double interest = this.accounts[i].monthlyInterest();
            this.accounts[i].balance -= fee;
            this.accounts[i].balance += this.accounts[i].balance * interest;
            System.out.println(this.accounts[i].toString());
        }
        System.out.println("*end of list*");
        System.out.println();
    }
}
