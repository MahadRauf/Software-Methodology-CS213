package com.example.group33project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

//import java.time.format.DateTimeFormatter;

/**
 * Controller for BankTeller application
 * @author Mahad rauf, Moeez Shahid
 */
public class BankTellerController {
    /**
     * Instance of AccountDatabase that will be used by the application to handle and store transactions
     */
    private AccountDatabase acctDB = new AccountDatabase();

    private static final double MIN_MM_INITIAL_DEPOSIT = 2500;
    private static final int loyal = 1;
    private static final int nonloyal = 0;
    private static final double GARBAGE_BAL = 2501;
    private static final int NEW_BRUNSWICK = 0;
    private static final int NEWARK = 1;
    private static final int CAMDEN = 2;

    /**
     * references the DatePicker on deposit/withdrawal tab
     */
    @FXML
    private DatePicker DOB_DW;

    /**
     * references the DatePicker on open/close tab
     */
    @FXML
    private DatePicker DOB_OC;

    /*@FXML
    private ToggleGroup OPEN_OR_CLOSE;*/

    /**
     * references the ToggleGroup for the account type on deposit/withdraw tab
     */
    @FXML
    private ToggleGroup accountTypeDW;

    /**
     * references the ToggleGroup for the account type on open/close tab
     */
    @FXML
    private ToggleGroup accountTypeOC;

    /**
     * references the amount TextField on deposit/withdraw tab
     */
    @FXML
    private TextField amount;

    /**
     * references the camden RadioButton on open/close tab
     */
    @FXML
    private RadioButton camden;

    /*@FXML
    private ToggleGroup campusCode;

    @FXML
    private RadioButton checkingDW;*/

    /**
     * references the checking RadioButton on open/close tab
     */
    @FXML
    private RadioButton checkingOC;

    /**
     * references the close RadioButton on open/close tab
     */
    @FXML
    private RadioButton closeOC;

    /*@FXML
    private RadioButton colCheckingDW;

    @FXML
    private RadioButton colCheckingOC;*/

    /**
     * references the first name TextField on deposit/withdraw tab
     */
    @FXML
    private TextField fNameDW;

    /**
     * references the first name TextField on open/close tab
     */
    @FXML
    private TextField fNameOC;

    /**
     * references the initial deposit TextField on open/close tab
     */
    @FXML
    private TextField initDeposit;

    /**
     * references the last name TextField on deposit/withdraw tab
     */
    @FXML
    private TextField lNameDW;

    /**
     * references the last name TextField on open/close tab
     */
    @FXML
    private TextField lNameOC;

    /**
     * references the loyalty RadioButton on open/close tab
     */
    @FXML
    private RadioButton loyalty;

    /*@FXML
    private RadioButton moneyMarketDW;*/

    /**
     * references the Money Market RadioButton on open/close tab
     */
    @FXML
    private RadioButton moneyMarketOC;

    /**
     * references the New Brunswick RadioButton on open/close tab
     */
    @FXML
    private RadioButton newBruns;

    /**
     * references the Newark RadioButton on open/close tab
     */
    @FXML
    private RadioButton newark;

    /**
     * references the open RadioButton on open/close tab
     */
    @FXML
    private RadioButton openOC;

    /*@FXML
    private RadioButton savingsDW;

    @FXML
    private RadioButton savingsOC;*/

    /**
     * references the TextArea that the application outputs to
     */
    @FXML
    private TextArea textArea;

    /**
     * resets the open/close tab and disables the initial deposit TextField
     * @param event upon selecting the close RadioButton
     */
    @FXML
    void disableDeposit(ActionEvent event) {
        disableOthers(event);
        reset(checkingOC, fNameOC, lNameOC, DOB_OC, initDeposit);
        initDeposit.setDisable(true);
        checkingOC.setSelected(true);
    }

    /**
     * resets a tab to its initial state
     * @param button button from a ToggleGroup that is to be selected
     * @param fName TextField to be cleared
     * @param lName TextField to be cleared
     * @param date DatePicker to be reset
     * @param amount TextField to be cleared
     */
    private void reset(RadioButton button, TextField fName, TextField lName, DatePicker date, TextField amount){
        button.setSelected(true);
        fName.clear();
        lName.clear();
        date.setValue(null);
        amount.clear();
    }

    /**
     * disables campus group and loyalty RadioButtons
     * @param event any account group RadioButton being selected
     */
    @FXML
    void disableOthers(ActionEvent event) {
        camden.setSelected(true);
        camden.setSelected(false);
        camden.setDisable(true);
        newark.setDisable(true);
        newBruns.setDisable(true);
        loyalty.setDisable(true);
        loyalty.setSelected(false);
    }

    /**
     * enables campus group RadioButtons
     * @param event upon College Checking RadioButton being selected
     */
    @FXML
    void enableColCode(ActionEvent event) {
        if(closeOC.isSelected()){
            return;
        }
        disableOthers(event);
        camden.setSelected(true);
        camden.setDisable(false);
        newark.setDisable(false);
        newBruns.setDisable(false);
    }

    /**
     * enables deposit TextField
     * @param event upon open radioButton being selected
     */
    @FXML
    void enableDeposit(ActionEvent event) {
        initDeposit.setDisable(false);
        checkingOC.setSelected(true);
    }

    /**
     * enables or selects loyalty RadioButton depending on certain conditions
     * @param event upon Money Market or Savings RadioButton being selected
     */
    @FXML
    void enableLoyalty(ActionEvent event) {
        if(closeOC.isSelected()){
            return;
        }
        disableOthers(event);
        if(moneyMarketOC.isSelected()){
            loyalty.setSelected(true);
        }else{
            loyalty.setDisable(false);
        }
    }

    /**
     * Checks if any of the given parameter are empty
     * @param fName TextField to check if empty
     * @param lName TextField to check if empty
     * @param DOB DatePicker to check if empty
     * @return true if any field is empty, false otherwise
     */
    private boolean hasEmptyField(TextField fName, TextField lName, DatePicker DOB){
        return fName.getText().trim().isEmpty() || lName.getText().trim().isEmpty() || DOB.getValue() == null;
    }

    /**
     * Creates and returns a Profile for a person based on the given parameters
     * @param fName first name of the person
     * @param lName last name of the person
     * @param DOB date of birth of the person
     * @return the Profile for the person if the date of birth is valid, null otherwise
     */
    private Profile createProfile(TextField fName, TextField lName, DatePicker DOB){
        String fNamePro = fName.getText().trim();
        String lNamePro = lName.getText().trim();
        Date DOBPro;
        // try catch probably not needed anymore, remove comment later after further testing if true
        //try{
            //DOBPro = new Date(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            DOBPro = new Date(DOB.getEditor().getText());
            //textArea.appendText(DOBPro.toString() + "\n");
        /*}catch(Exception ex){
            textArea.appendText("Date of birth invalid.\n");
            return null;
        }*/
        if(!DOBPro.isValid() || DOBPro.isFutureDate() || DOBPro.isToday()){
            textArea.appendText("Date of birth invalid.\n");
            return null;
        }
        Profile ret = new Profile(fNamePro, lNamePro, DOBPro);
        return ret;
    }

    /**
     * makes the text from the parameter TextField into a double and returns it
     * @param amount TextField to get the return double from
     * @return the TextField as a double if possible
     * @throws NumberFormatException throws Exception to calling class if item in TextField cannot be made a double
     */
    private double getAmount(TextField amount) throws NumberFormatException{
        String text = amount.getText().trim();
        double ret;
        try{
            ret = Double.parseDouble(text);
        }catch(NumberFormatException ex){
            textArea.appendText("Not a valid amount.\n");
            throw new NumberFormatException();
        }
        return ret;
    }

    /**
     * returns the integer value of the campus selected, 0: New Brunswick, 1: Newark, 2: Camden
     * @return integer value of the selected campus
     */
    private int getCode(){
        if(camden.isSelected()){
            return CAMDEN;
        }else if(newark.isSelected()){
            return NEWARK;
        }else{
            return NEW_BRUNSWICK;
        }
    }

    /**
     * checks if the parameter deposit is a valid Money Market deposit (greater than or equal to 2500)
     * @param deposit amount to be checked for validity
     * @return true if valid, false otherwise
     */
    private boolean validMMDeposit(double deposit){
        if(deposit < MIN_MM_INITIAL_DEPOSIT){
            textArea.appendText("Minimum of $2500 to open a MoneyMarket account.\n");
            return false;
        }
        return true;
    }

    /**
     * Opens/Reopens an account in the AccountDatabase
     * @param acctToAdd account to open
     */
    private void addAcct(Account acctToAdd){
        Account dbAccount = acctDB.getAcct(acctToAdd);
        if(dbAccount != null){
            if(dbAccount.closed){
                acctDB.open(acctToAdd);
                textArea.appendText("Account reopened.\n");
            }else{
                textArea.appendText(dbAccount.holder.toString() + " same account(type) is in the database.\n");
            }
            return;
        }
        if(acctToAdd instanceof Checking){
            Account simAccount = acctDB.findSimilarChecking(acctToAdd);
            if(simAccount != null){
                textArea.appendText(simAccount.holder.toString() + " same account(type) is in the database.\n");
                return;
            }
        }
        acctDB.open(acctToAdd);
        textArea.appendText("Account opened.\n");
    }

    /**
     * Creates an account from the parameters given
     * @param group ToggleGroup which specifies the account type
     * @param person person to be associated with the account
     * @param balance balance that the account will have
     * @return
     */
    private Account createAccount(ToggleGroup group, Profile person, double balance){
        Account acct = null;
        RadioButton selectedButton = (RadioButton) group.getSelectedToggle();
        String acctType = selectedButton.getText();
        if(acctType.equals("Checking")){
            acct = new Checking(person, balance);
        }else if(acctType.equals("College Checking")){
            int colCode = getCode();
            acct = new CollegeChecking(person, balance, colCode);
        }else if(acctType.equals("Money Market")){
            acct = new MoneyMarket(person, balance);
        }else if(acctType.equals("Savings")){
            int loyalCode = loyalty.isSelected() ? loyal : nonloyal;
            acct = new Savings(person, balance, loyalCode);
        }
        return acct;
    }

    /**
     * opens ann account in the AccountDatabase
     */
    private void openAcct(){
        if(hasEmptyField(fNameOC, lNameOC, DOB_OC) || initDeposit.getText().trim().isEmpty()){
            textArea.appendText("Missing data for opening an account.\n");
            return;
        }
        double deposit;
        try{
            deposit = getAmount(initDeposit);
        }catch(NumberFormatException ex){
            return;
        }
        if(deposit <= 0){
            textArea.appendText("Initial Deposit cannot be 0 or negative\n");
            return;
        }
        if(moneyMarketOC.isSelected() && !validMMDeposit(deposit)){
            return;
        }
        Profile toAdd = createProfile(fNameOC, lNameOC, DOB_OC);
        if(toAdd == null){
            return;
        }
        Account acctToAdd = createAccount(accountTypeOC, toAdd, deposit);
        if(acctToAdd != null){
            addAcct(acctToAdd);
        }
    }

    /**
     * closes and account in the AccountDatabase
     */
    private void closeAcct(){
        if(hasEmptyField(fNameOC, lNameOC, DOB_OC)){
            textArea.appendText("Missing data for closing an account.\n");
            return;
        }
        Profile toAdd = createProfile(fNameOC, lNameOC, DOB_OC);
        if(toAdd == null){
            return;
        }
        Account acctToDel = createAccount(accountTypeOC, toAdd, GARBAGE_BAL);
        Account dbAccount = acctDB.getAcct(acctToDel);
        if(dbAccount != null){
            if(dbAccount.closed){
                textArea.appendText("Account is closed already.\n");
            }else{
                acctDB.close(dbAccount);
                textArea.appendText("Account closed.\n");
            }
            return;
        }
        textArea.appendText(acctToDel.holder.toString() + " " + acctToDel.getType() + " is not in the database.\n");
    }

    /**
     * opens or closes an account depending on which RadioButton is selected
     * @param event upon selecting the submit button
     */
    @FXML
    void onSubmitPress(ActionEvent event) {
        if(openOC.isSelected()){
            openAcct();
        }else{
            closeAcct();
        }
        disableOthers(event);
        reset(checkingOC, fNameOC, lNameOC, DOB_OC, initDeposit);
    }

    /**
     * deposits into a given account
     * @param event upon selecting the deposit button
     */
    @FXML
    void deposit(ActionEvent event){
        if(hasEmptyField(fNameDW, lNameDW, DOB_DW) || amount.getText().trim().isEmpty()){
            textArea.appendText("Missing data for deposit.\n");
            return;
        }
        Profile toDep = createProfile(fNameDW, lNameDW, DOB_DW);
        if(toDep == null){
            return;
        }
        double deposit;
        try{
            deposit = getAmount(amount);
        }catch(NumberFormatException ex){
            return;
        }
        if(deposit <= 0){
            textArea.appendText("Deposit - amount cannot be 0 or negative.\n");
            return;
        }
        Account acctToDep = createAccount(accountTypeDW, toDep, deposit);
        Account dbAccount = acctDB.getAcct(acctToDep);
        if(dbAccount != null){
            if(dbAccount.closed){
                textArea.appendText("Account is closed.\n");
            }else{
                acctDB.deposit(acctToDep);
                textArea.appendText("Deposit - balance updated.\n");
            }
            return;
        }
        textArea.appendText(acctToDep.holder.toString() + " " + acctToDep.getType() + " is not in the database.\n");
    }

    /**
     * withdraws from a given account
     * @param event upon selecting withdraw button
     */
    @FXML
    void withdraw(ActionEvent event){
        if(hasEmptyField(fNameDW, lNameDW, DOB_DW) || amount.getText().trim().isEmpty()){
            textArea.appendText("Missing data for withdrawal.\n");
            return;
        }
        Profile toWith = createProfile(fNameDW, lNameDW, DOB_DW);
        if(toWith == null){
            return;
        }
        double withdrawal;
        try{
            withdrawal = getAmount(amount);
        }catch(NumberFormatException ex){
            return;
        }
        if(withdrawal <= 0){
            textArea.appendText("Withdrawal - amount cannot be 0 or negative.\n");
            return;
        }
        Account acctToWith = createAccount(accountTypeDW, toWith, withdrawal);
        Account dbAccount = acctDB.getAcct(acctToWith);
        if(dbAccount != null){
            if(dbAccount.closed){
                textArea.appendText("Account is closed.\n");
            }else{
                acctDB.withdraw(acctToWith);
                textArea.appendText("Withdrawal - balance updated.\n");
            }
            return;
        }
        textArea.appendText(acctToWith.holder.toString() + " " + acctToWith.getType() + " is not in the database.\n");
    }

    /**
     * prints all the accounts into the TextArea
     * @param event upon selecting Print All Accounts button
     */
    @FXML
    void printAll(ActionEvent event){
        textArea.appendText(acctDB.print());
    }

    /**
     * prints all the accounts into the TextArea ordered by account Type
     * @param event upon selecting Print All By Account Type button
     */
    @FXML
    void printAllByAcct(ActionEvent event){
        textArea.appendText(acctDB.printByAccountType());
    }

    /**
     * prints all the accounts into the TextArea with fees and interest to be applied
     * @param event upon selecting Compute Fees and Print button
     */
    @FXML
    void printAllWithFees(ActionEvent event){
        textArea.appendText(acctDB.printFeeAndInterest());
    }

    /**
     * prints all the accounts into the TextArea after applying fees and interest
     * @param event upon selecting Print All After Update button
     */
    @FXML
    void printAllAfterUpdate(ActionEvent event){
        textArea.appendText(acctDB.updateAndPrint());
    }
}

