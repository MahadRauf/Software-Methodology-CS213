package com.example.group33project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public class HelloController {

    private AccountDatabase acctDB = new AccountDatabase();

    private static final double MIN_MM_INITIAL_DEPOSIT = 2500;
    private static final int loyal = 1;
    private static final int nonloyal = 0;
    private static final double GARBAGE_BAL = 100;
    private static final double GARBAGE_BAL_MM = 2501;

    @FXML
    private DatePicker DOB_DW;

    @FXML
    private DatePicker DOB_OC;

    @FXML
    private ToggleGroup OPEN_OR_CLOSE;

    @FXML
    private ToggleGroup accountTypeDW;

    @FXML
    private ToggleGroup accountTypeOC;

    @FXML
    private TextField amount;

    @FXML
    private RadioButton camden;

    @FXML
    private ToggleGroup campusCode;

    @FXML
    private RadioButton checkingDW;

    @FXML
    private RadioButton checkingOC;

    @FXML
    private RadioButton closeOC;

    @FXML
    private RadioButton colCheckingDW;

    @FXML
    private RadioButton colCheckingOC;

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

    @FXML
    private RadioButton moneyMarketDW;

    @FXML
    private RadioButton moneyMarketOC;

    @FXML
    private RadioButton newBruns;

    @FXML
    private RadioButton newark;

    @FXML
    private RadioButton openOC;

    @FXML
    private RadioButton savingsDW;

    @FXML
    private RadioButton savingsOC;

    @FXML
    private TextArea textArea;


    @FXML
    void disableDeposit(ActionEvent event) {
        disableOthers(event);
        initDeposit.setDisable(true);
        checkingOC.setSelected(true);
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

    boolean hasEmptyField(TextField fName, TextField lName, DatePicker DOB){
        if(fName.getText().trim().isEmpty() || lName.getText().trim().isEmpty() || DOB.getValue() == null){
            return true;
        }
        return false;
    }

    Profile createProfile(TextField fName, TextField lName, DatePicker DOB){
        String fNamePro = fName.getText().trim();
        String lNamePro = lName.getText().trim();
        Date DOBPro;
        try{
            DOBPro = new Date(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }catch(Exception ex){
            textArea.appendText("Date of birth invalid.\n");
            return null;
        }
        if(!DOBPro.isValid() || DOBPro.isFutureDate() || DOBPro.isToday()){
            textArea.appendText("Date of birth invalid.\n");
            return null;
        }
        Profile ret = new Profile(fNamePro, lNamePro, DOBPro);
        return ret;
    }

    double getAmount(TextField amount) throws NumberFormatException{
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

    int getCode(){
        if(camden.isSelected()){
            return 2; // make not a magic number
        }else if(newark.isSelected()){
            return 1;
        }else if(newBruns.isSelected()){
            return 0;
        }
        return -1;
    }

    private boolean validMMDeposit(double deposit){
        if(deposit < MIN_MM_INITIAL_DEPOSIT){
            textArea.appendText("Minimum of $2500 to open a MoneyMarket account.\n");
            return false;
        }
        return true;
    }

    void addAcct(Account acctToAdd){
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

    void openAcct(){
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
        Account acctToAdd = null;
        Profile toAdd = createProfile(fNameOC, lNameOC, DOB_OC);
        if(toAdd == null){
            return;
        }
        if(deposit <= 0){
            textArea.appendText("Initial Deposit cannot be 0 or negative\n");
            return;
        }
        RadioButton selectedButton = (RadioButton) accountTypeOC.getSelectedToggle();
        String acctType = selectedButton.getText();
        if(acctType.equals("Checking")){
            acctToAdd = new Checking(toAdd, deposit);
        }else if(acctType.equals("College Checking")){
            int colCode = getCode();
            acctToAdd = new CollegeChecking(toAdd, deposit, colCode);
        }else if(acctType.equals("Money Market")){
            if(validMMDeposit(deposit)){
                acctToAdd = new MoneyMarket(toAdd, deposit);
            }
        }else if(acctType.equals("Savings")){
            int loyalCode = loyalty.isSelected() ? loyal : nonloyal;
            acctToAdd = new Savings(toAdd, deposit, loyalCode);
        }
        if(acctToAdd != null){
            addAcct(acctToAdd);
        }
    }

    void closeAcct(){
        if(hasEmptyField(fNameOC, lNameOC, DOB_OC) || initDeposit.getText().trim().isEmpty()){
            textArea.appendText("Missing data for closing an account.\n");
            return;
        }
        Account acctToDel = null;
        Profile toAdd = createProfile(fNameOC, lNameOC, DOB_OC);
        if(toAdd == null){
            return;
        }
        RadioButton selectedButton = (RadioButton) accountTypeOC.getSelectedToggle();
        String acctType = selectedButton.getText();
        if(acctType.equals("Checking")){
            acctToDel = new Checking(toAdd, GARBAGE_BAL);
        }else if(acctType.equals("College Checking")){
            int colCode = getCode();
            acctToDel = new CollegeChecking(toAdd, GARBAGE_BAL, colCode);
        }else if(acctType.equals("Money Market")){
            acctToDel = new MoneyMarket(toAdd, GARBAGE_BAL_MM);
        }else if(acctType.equals("Savings")){
            int loyalCode = loyalty.isSelected() ? loyal : nonloyal;
            acctToDel = new Savings(toAdd, GARBAGE_BAL, loyalCode);
        }
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
        checkingOC.setSelected(true);
        fNameOC.clear();
        lNameOC.clear();
        DOB_OC.setValue(null);
        initDeposit.clear();
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

