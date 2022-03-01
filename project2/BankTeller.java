package project2;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * User interface class to process transactions through the console which outputs to the console. Can take transactioons one-by-one or
 * multiple at a time.
 * @author Mahad Rauf, Moeez Shahid
 */
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
    private static final double MIN_MM_INITIAL_DEPOSIT = 2500;
    private static final int CLOSE_MIN_ITEMS_REQUIRED = 5;
    private static final int DEP_WDRW_MIN_ITEMS_REQUIRED = 6;
    private static final double DEP_WITH_MIN = 0;
    private static final int INVALID_CAMPUS = -1;


    /**
     * Creates a profile from the array of strings command.
     * @param command String array which has the information necessary to make a profile
     * @return Profile that is created if all the information is valid, false otherwise.
     */
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

    /**
     * Takes a string and returns it as a double.
     * @param deposit string to convert into a double.
     * @return double representation of the String. If a NumberFormatException occurs, returns -1.0;
     */
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

    /**
     * Gets the code from command[6] of the command array and returns it as an integer.
     * @param command String array that has the code in array[6]
     * @return integer representation of the string in command[6]. If a NumberFormatException or NoSuchElementException occurs, returns -1.
     */
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

    /**
     * Checks the parameter loyalCode if it is one of 0 or 1.
     * @param loyalCode code to check validity
     * @return true if it is valid, false otherwise.
     */
    private boolean validLoyal(int loyalCode){
        if(loyalCode != LOYAL && loyalCode != NOT_LOYAL){
            System.out.println("Invalid loyalty code.");
            return false;
        }
        return true;
    }

    /**
     * Checks the parameter deposit whether it is greater than 2500.
     * @param deposit amount to check
     * @return true if deposit greater than or equal to 2500, false otherwise.
     */
    private boolean validMMDeposit(double deposit){
        if(deposit < MIN_MM_INITIAL_DEPOSIT){
            System.out.println("Minimum of $2500 to open a MoneyMarket account.");
            return false;
        }
        return true;
    }

    /**
     * Opens or reopens the parameter account in the account database.
     * @param acctToAdd account to add
     * @param acctDB account database which the account will be added to
     */
    private void addAcct(Account acctToAdd, AccountDatabase acctDB){
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
        if(acctToAdd instanceof Checking){

            Account simAccount = acctDB.findSimilarChecking(acctToAdd);
            if(simAccount != null){
                System.out.println(simAccount.holder.toString() + " same account(type) is in the database.");
                return;
            }
        }
        acctDB.open(acctToAdd);
        System.out.println("Account opened.");
    }

    /**
     * Constructs an account from the command array and adds it to the account database if it is a valid account to add.
     * @param command string array which has the necessary items in the right order to make the account.
     * @param acctDB account database to which the account will be added
     */
    public void openAcct(String [] command, AccountDatabase acctDB){
        if(command.length < OPEN_MIN_ITEMS_REQUIRED){
            System.out.println("Missing data for opening an account.");
            return;
        }
        Profile toAdd = createProfile(command);
        double deposit = getAmount(command[5]);
        if(toAdd == null || deposit == EXCEPTION){
            return;
        }
        if(deposit <= 0){
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        }
        String acctType = command[1];
        Account acctToAdd = null;
        if(acctType.equals(CHECKING)){
            acctToAdd = new Checking(toAdd, deposit);
        }else if(acctType.equals(COLLEGE_CHECKING)){
            int colCode = getCode(command);
            if(colCode < MIN_COL_CODE || colCode > MAX_COL_CODE){
                System.out.println("Invalid campus code.");
                return;
            }
            acctToAdd = new CollegeChecking(toAdd, deposit, colCode);
        }else if(acctType.equals(SAVING)){
            int loyalCode = getCode(command);
            if(validLoyal(loyalCode)){
                acctToAdd = new Savings(toAdd, deposit, loyalCode);
            }
        }else if(acctType.equals(MONEY_MARKET)){
            if(validMMDeposit(deposit)){
                acctToAdd = new MoneyMarket(toAdd, deposit);
            }
        }else{
            System.out.println("Invalid account type!");
        }
        if(!(acctToAdd == null)){
            addAcct(acctToAdd, acctDB);
        }
    }

    /**
     * Constructs an account from the command array and closes it in the account database if it is a valid account to close.
     * @param command string array which has the necessary items in the right order to make the account.
     * @param acctDB account database where the account will be closed.
     */
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

    /**
     * applies interest and fees to all the accounts in the database and then prints them
     * @param acctDB account database that is to be updated
     */
    public void addFees(AccountDatabase acctDB){
        acctDB.updateAndPrint();
    }

    /**
     * Constructs an account from the command array and then finds it in the database to update the balance of the account with the balance
     * of the account created.
     * @param command string array which has the necessary items in the right order to make the account.
     * @param acctDB account database where the account to deposit to is.
     */
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

    /**
     * Constructs an account from the command array and then finds it in the database to update the balance of the account with the balance
     * of the account created.
     * @param command string array which has the necessary items in the right order to make the account.
     * @param acctDB account database where the account to withdraw from is.
     */
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

    /**
     * Creates the instance of AccountDatabase which the program will use and takes user input to be processed.
     */
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
