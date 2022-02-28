package project2;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankTeller {
    private static final String OPEN = "O";
    private static final String CLOSE = "C";
    private static final String DEPOSIT = "D";
    private static final String WITHDRAW = "W";
    private static final String DISPLAY_ALL = "P";
    private static final String DISPLAY_ALL_BY_ACCOUNT = "PT";
    private static final String DISPLAY_ALL_WITH_FEE_INT = "PI";
    private static final String UPDATE_ALL = "UB";
    private static final String QUIT = "Q";
    private static final int OPEN_MIN_ITEMS_REQUIRED = 6;
    private static final String CHECKING = "C";
    private static final String COLLEGE_CHECKING = "CC";
    private static final String SAVING = "S";
    private static final String MONEY_MARKET = "MM";
    private static final double EXCEPTION = -1.0;
    private static final int MIN_COL_CODE = 0;
    private static final int MAX_COL_CODE = 2;
    private static final int LOYAL = 1;
    private static final int NOT_LOYAL = 0;
    private static final int MIN_MM_INITIAL_DEPOSIT = 2500;
    private static final int CLOSE_MIN_ITEMS_REQUIRED = 5;
    private static final int DEP_WDRW_MIN_ITEMS_REQUIRED = 6;
    private static final double DEP_WITH_MIN = 0;
    private static final int INVALID_CAMPUS = -1;



    private Profile createProfile(String [] command){
        String fname = command[2];
        String lname = command[3];
        Date dob = new Date(command[4]);
        if(!dob.isValid() || dob.isFutureDate() || dob.isToday()){
            System.out.println("Date of birth invalid.");
            return null;
        }
        Profile ret = new Profile(fname, lname, dob);
        return ret;
    }

    private double getAmount(String deposit){
        double ret;
        try{
            ret = Double.parseDouble(deposit);
        }catch(NumberFormatException ex){
            System.out.println("Not a valid amount.");
            return EXCEPTION;
        }
        return ret;
    }

    private void openChecking(String [] command, AccountDatabase acctDB){
        Profile toAdd = createProfile(command);
        double deposit = getAmount(command[5]);
        if(toAdd == null || deposit == EXCEPTION){
            return;
        }
        if(deposit <= 0){
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        }
        Checking acctToAdd = new Checking(toAdd, deposit);
        Account dbAccount = acctDB.getAcct(acctToAdd);
        if(dbAccount != null){
            if(dbAccount.closed){
                acctDB.open(acctToAdd);
                System.out.println("Account reopened.");
            }else{
                System.out.println(dbAccount.holder.toString() + " same account(type) is in the database.");
            }
            return;
        }
        Account simAccount = acctDB.findSimilarChecking(acctToAdd);
        if(simAccount != null){
            System.out.println(simAccount.holder.toString() + " same account(type) is in the database.");
            return;
        }
        acctDB.open(acctToAdd);
        System.out.println("Account opened.");

    }

    private int getCode(String [] command){
        int ret = INVALID_CAMPUS;
        try{
            ret = Integer.parseInt(command[6]);
        }catch(NoSuchElementException ex){
            System.out.println("Missing data for opening an account.");
            return ret;
        }catch(NumberFormatException ex){
            System.out.println("Invalid campus code.");
            return ret;
        }
        return ret;
    }

    private void openColChecking(String [] command, AccountDatabase acctDB){
        Profile toAdd = createProfile(command);
        double deposit = getAmount(command[5]);
        if(toAdd == null || deposit == EXCEPTION){
            return;
        }
        if(deposit <= 0){
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        }
        int colCode = getCode(command);
        if(colCode < MIN_COL_CODE || colCode > MAX_COL_CODE){
            System.out.println("Invalid campus code.");
            return;
        }
        CollegeChecking acctToAdd = new CollegeChecking(toAdd, deposit, colCode);
        Account dbAccount = acctDB.getAcct(acctToAdd);
        if(dbAccount != null){
            if(dbAccount.closed){
                acctDB.open(acctToAdd);
                System.out.println("Account reopened.");
            }else{
                System.out.println(dbAccount.holder.toString() + " same account(type) is in the database.");
            }
            return;
        }
        Account simAccount = acctDB.findSimilarChecking(acctToAdd);
        if(simAccount != null){
            System.out.println(simAccount.holder.toString() + " same account(type) is in the database.");
            return;
        }
        acctDB.open(acctToAdd);
        System.out.println("Account opened.");
    }

    private void openSaving(String [] command, AccountDatabase acctDB){
        Profile toAdd = createProfile(command);
        double deposit = getAmount(command[5]);
        if(toAdd == null || deposit == EXCEPTION){
            return;
        }
        if(deposit <= 0){
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        }
        int loyalCode = getCode(command);
        if(loyalCode != LOYAL && loyalCode != NOT_LOYAL){
            System.out.println("Invalid loyalty code.");
            return;
        }
        Savings acctToAdd = new Savings(toAdd, deposit, loyalCode);
        Account dbAccount = acctDB.getAcct(acctToAdd);
        if(dbAccount != null){
            if(dbAccount.closed){
                acctDB.open(acctToAdd);
                System.out.println("Account reopened.");
            }else{
                System.out.println(dbAccount.holder.toString() + " same account(type) is in the database.");
            }
            return;
        }
        acctDB.open(acctToAdd);
        System.out.println("Account opened.");
    }

    private void openMM(String [] command, AccountDatabase acctDB){
        Profile toAdd = createProfile(command);
        double deposit = getAmount(command[5]);
        if(toAdd == null || deposit == EXCEPTION){
            return;
        }
        if(deposit <= 0){
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        }
        if(deposit < MIN_MM_INITIAL_DEPOSIT){
            System.out.println("Minimum of $2500 to open a MoneyMarket account.");
            return;
        }
        MoneyMarket acctToAdd = new MoneyMarket(toAdd, deposit);
        Account dbAccount = acctDB.getAcct(acctToAdd);
        if(dbAccount != null){
            if(dbAccount.closed){
                acctDB.open(acctToAdd);
                System.out.println("Account reopened.");
            }else{
                System.out.println(dbAccount.holder.toString() + " same account(type) is in the database.");
            }
            return;
        }
        acctDB.open(acctToAdd);
        System.out.println("Account opened.");
    }

    public void openAcct(String [] command, AccountDatabase acctDB){
        if(command.length < OPEN_MIN_ITEMS_REQUIRED){
            System.out.println("Missing data for opening an account.");
            return;
        }
        String acctType = command[1];
        if(acctType.equals(CHECKING)){
            openChecking(command, acctDB);
        }else if(acctType.equals(COLLEGE_CHECKING)){
            openColChecking(command, acctDB);
        }else if(acctType.equals(SAVING)){
            openSaving(command, acctDB);
        }else if(acctType.equals(MONEY_MARKET)){
            openMM(command, acctDB);
        }else{
            System.out.println("Invalid account type!");
        }
    }

    public void closeAcct(String [] command, AccountDatabase acctDB){
        if(command.length < CLOSE_MIN_ITEMS_REQUIRED){
            System.out.println("Missing data for closing an account.");
            return;
        }
        String acctType = command[1];
        Profile toDel = createProfile(command);
        if(toDel == null){
            return;
        }
        Account acctToDel;
        if(acctType.equals(CHECKING)){
            acctToDel = new Checking(toDel, 100); // make account with garbage data to search
        }else if(acctType.equals(COLLEGE_CHECKING)){
            acctToDel = new CollegeChecking(toDel, 100, 0); // make account with garbage data to search
        }else if(acctType.equals(SAVING)){
            acctToDel = new Savings(toDel, 100); // make account with garbage data to search
        }else if(acctType.equals(MONEY_MARKET)){
            acctToDel = new MoneyMarket(toDel, 2501); // make account with garbage data to search
        }else{
            System.out.println("Invalid account type!");
            return;
        }
        Account dbAccount = acctDB.getAcct(acctToDel);
        if(dbAccount != null){
            if(dbAccount.closed){
                System.out.println("Account is closed already.");
            }else{
                acctDB.close(dbAccount);
                System.out.println("Account closed.");
            }
            return;
        }
        System.out.println(acctToDel.holder.toString() + " " + acctToDel.getType() + " is not in the database.");
    }

    public void addFees(AccountDatabase acctDB){
        acctDB.updateAndPrint();
    }

    public void deposit(String [] command, AccountDatabase acctDB){
        if(command.length < DEP_WDRW_MIN_ITEMS_REQUIRED){
            System.out.println("Missing data for deposit.");
            return;
        }
        String acctType = command[1];
        Profile toDep = createProfile(command);
        double deposit = getAmount(command[5]);
        if(toDep == null || deposit == EXCEPTION){
            return;
        }
        if(deposit <= DEP_WITH_MIN){
            System.out.println("Deposit - amount cannot be 0 or negative.");
            return;
        }
        Account acctToDep;
        if(acctType.equals(CHECKING)){
            acctToDep = new Checking(toDep, deposit); // make account with garbage data to search
        }else if(acctType.equals(COLLEGE_CHECKING)){
            acctToDep = new CollegeChecking(toDep, deposit, 0); // make account with garbage data to search
        }else if(acctType.equals(SAVING)){
            acctToDep = new Savings(toDep, deposit); // make account with garbage data to search
        }else if(acctType.equals(MONEY_MARKET)){
            acctToDep = new MoneyMarket(toDep, deposit); // make account with garbage data to search
        }else{
            System.out.println("Invalid account type!");
            return;
        }
        Account dbAccount = acctDB.getAcct(acctToDep);
        if(dbAccount != null){
            if(dbAccount.closed){
                System.out.println("Account is closed.");
            }else{
                acctDB.deposit(acctToDep);
                System.out.println("Deposit - balance updated.");
            }
            return;
        }
        System.out.println(acctToDep.holder.toString() + " " + acctToDep.getType() + " is not in the database.");
    }

    public void withdraw(String [] command, AccountDatabase acctDB){
        if(command.length < DEP_WDRW_MIN_ITEMS_REQUIRED){
            System.out.println("Missing data for deposit.");
            return;
        }
        String acctType = command[1];
        Profile toWith = createProfile(command);
        double withdrawal = getAmount(command[5]);
        if(toWith == null || withdrawal == EXCEPTION){
            return;
        }
        if(withdrawal <= DEP_WITH_MIN){
            System.out.println("Withdraw - amount cannot be 0 or negative.");
            return;
        }
        Account acctToWith;
        if(acctType.equals(CHECKING)){
            acctToWith = new Checking(toWith, withdrawal); // make account with garbage data to search
        }else if(acctType.equals(COLLEGE_CHECKING)){
            acctToWith = new CollegeChecking(toWith, withdrawal, 0); // make account with garbage data to search
        }else if(acctType.equals(SAVING)){
            acctToWith = new Savings(toWith, withdrawal); // make account with garbage data to search
        }else if(acctType.equals(MONEY_MARKET)){
            acctToWith = new MoneyMarket(toWith, withdrawal); // make account with garbage data to search
        }else{
            System.out.println("Invalid account type!");
            return;
        }
        Account dbAccount = acctDB.getAcct(acctToWith);
        if(dbAccount != null){
            if(dbAccount.closed){
                System.out.println("Account is closed.");
            }else{
                boolean success = acctDB.withdraw(acctToWith);
                if(success){
                    System.out.println("Withdraw - balance updated.");
                }else{
                    System.out.println("Withdraw - insufficient fund.");
                }
            }
            return;
        }
        System.out.println(acctToWith.holder.toString() + " " + acctToWith.getType() + " is not in the database.");
    }

    /**
     * Takes a command in the form "mainCommand ....." that uses the first word of the command to determine if it is a valid
     * command and then carry out the command.
     * @param command string array with tokenized form of the string "mainCommand .....".
     * @param acctDB the account database on which the command will be carried out on.
     * @return false if "Q" was the command given, true otherwise
     */
    private boolean getAction(String [] command, AccountDatabase acctDB){
        String cmd = command[0];
        boolean ret = true;
        if(cmd.equals(OPEN)){
            openAcct(command, acctDB);
        }else if(cmd.equals(CLOSE)){
            closeAcct(command, acctDB);
        }else if(cmd.equals(DEPOSIT)){
            deposit(command, acctDB);
        }else if(cmd.equals(WITHDRAW)){
            withdraw(command, acctDB);
        }else if(cmd.equals(DISPLAY_ALL)){
            acctDB.print();
        }else if(cmd.equals(DISPLAY_ALL_BY_ACCOUNT)){
            acctDB.printByAccountType();
        }else if(cmd.equals(DISPLAY_ALL_WITH_FEE_INT)){
            acctDB.printFeeAndInterest();
        }else if(cmd.equals(UPDATE_ALL)){
            addFees(acctDB);
        }else if(cmd.equals(QUIT)){
            System.out.println("Bank Teller is terminated.");
            ret = false;
        }else{
            System.out.println("Invalid command!");
        }
        return ret;
    }

    public void run(){
        System.out.println("Bank teller is running.");
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        AccountDatabase acctDB = new AccountDatabase();
        while(loop){
            String command = sc.nextLine();
            if(command.length() == 0){
                continue;
            }
            String [] commandSplit = command.split("\\s+");
            loop = getAction(commandSplit, acctDB);
        }
    }
}
