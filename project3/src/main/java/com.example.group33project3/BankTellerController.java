package com.example.group33project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

//import java.time.format.DateTimeFormatter;

public class BankTellerController {

    private AccountDatabase acctDB = new AccountDatabase();

    private static final double MIN_MM_INITIAL_DEPOSIT = 2500;
    private static final int loyal = 1;
    private static final int nonloyal = 0;
    private static final double GARBAGE_BAL = 2501;

    @FXML
    private DatePicker DOB_DW;

    @FXML
    private DatePicker DOB_OC;

    /*@FXML
    private ToggleGroup OPEN_OR_CLOSE;*/

    @FXML
    private ToggleGroup accountTypeDW;

    @FXML
    private ToggleGroup accountTypeOC;

    @FXML
    private TextField amount;

    @FXML
    private RadioButton camden;

    /*@FXML
    private ToggleGroup campusCode;

    @FXML
    private RadioButton checkingDW;*/

    @FXML
    private RadioButton checkingOC;

    @FXML
    private RadioButton closeOC;

    /*@FXML
    private RadioButton colCheckingDW;

    @FXML
    private RadioButton colCheckingOC;*/

    @FXML
    private TextField fNameDW;

    @FXML
    private TextField fNameOC;

    @FXML
    private TextField initDeposit;

    @FXML
    private TextField lNameDW;

    @FXML
    private TextField lNameOC;

    @FXML
    private RadioButton loyalty;

    /*@FXML
    private RadioButton moneyMarketDW;*/

    @FXML
    private RadioButton moneyMarketOC;

    @FXML
    private RadioButton newBruns;

    @FXML
    private RadioButton newark;

    @FXML
    private RadioButton openOC;

    /*@FXML
    private RadioButton savingsDW;

    @FXML
    private RadioButton savingsOC;*/

    @FXML
    private TextArea textArea;


    @FXML
    void disableDeposit(ActionEvent event) {
        disableOthers(event);
        reset(checkingOC, fNameOC, lNameOC, DOB_OC, initDeposit);
        initDeposit.setDisable(true);
        checkingOC.setSelected(true);
    }

    private void reset(RadioButton button, TextField fName, TextField lName, DatePicker date, TextField amount){
        button.setSelected(true);
        fName.clear();
        lName.clear();
        date.setValue(null);
        amount.clear();
    }

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

    @FXML
    void enableDeposit(ActionEvent event) {
        initDeposit.setDisable(false);
        checkingOC.setSelected(true);
    }

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

    private boolean hasEmptyField(TextField fName, TextField lName, DatePicker DOB){
        return fName.getText().trim().isEmpty() || lName.getText().trim().isEmpty() || DOB.getValue() == null;
    }

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

    private int getCode(){
        if(camden.isSelected()){
            return 2; // make not a magic number
        }else if(newark.isSelected()){
            return 1;
        }else{
            return 0;
        }
    }

    private boolean validMMDeposit(double deposit){
        if(deposit < MIN_MM_INITIAL_DEPOSIT){
            textArea.appendText("Minimum of $2500 to open a MoneyMarket account.\n");
            return false;
        }
        return true;
    }

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

    @FXML
    void printAll(ActionEvent event){
        textArea.appendText(acctDB.print());
    }

    @FXML
    void printAllByAcct(ActionEvent event){
        textArea.appendText(acctDB.printByAccountType());
    }

    @FXML
    void printAllWithFees(ActionEvent event){
        textArea.appendText(acctDB.printFeeAndInterest());
    }

    @FXML
    void printAllAfterUpdate(ActionEvent event){
        textArea.appendText(acctDB.updateAndPrint());
    }
}

