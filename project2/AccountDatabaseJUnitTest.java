package project2;

import org.junit.Assert;
import org.junit.Test;

public class AccountDatabaseJUnitTest {
	
	@Test
    public void closeCheckingFromEmptyDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Checking bankster = new Checking(profile, 500);
        Assert.assertFalse(acctDB.close(bankster));
        System.out.println("closeCheckingFromEmptyDatabaseTest passed.");
    }
	
	@Test
	public void closeCollegeCheckingFromEmptyDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        CollegeChecking bankster = new CollegeChecking(profile, 500, 0);
        Assert.assertFalse(acctDB.close(bankster));
        System.out.println("closeCollegeCheckingFromEmptyDatabaseTest passed.");
    }
	
	@Test
	public void closeLoyalSavingsFromEmptyDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Savings bankster = new Savings(profile, 500, 1);
        Assert.assertFalse(acctDB.close(bankster));
        System.out.println("closeLoyalSavingsFromEmptyDatabaseTest passed.");
    }
	
	@Test
	public void closeDisloyalSavingsFromEmptyDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Savings bankster = new Savings(profile, 500, 0);
        Assert.assertFalse(acctDB.close(bankster));
        System.out.println("closeDisloyalSavingsFromEmptyDatabaseTest passed.");
    }
	@Test
	public void closeMoneyMarketFromEmptyDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 2900);
        Assert.assertFalse(acctDB.close(bankster));
        System.out.println("closeMoneyMarketFromEmptyDatabaseTest passed.");
    }
	
	@Test
	public void closeMoneyMarketUnder2500FromEmptyDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 200);
        Assert.assertFalse(acctDB.close(bankster));
        System.out.println("closeMoneyMarketUnder2500FromEmptyDatabaseTest passed.");
    }
	
	@Test
    public void openCheckingInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Checking bankster = new Checking(profile, 500);
        Assert.assertTrue(acctDB.open(bankster));
        System.out.println("openCheckingInDatabaseTest passed.");
    }
	
	@Test
    public void openCollegeCheckingInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        CollegeChecking bankster = new CollegeChecking(profile, 500, 0);
        Assert.assertTrue(acctDB.open(bankster));
        System.out.println("openCollegeCheckingInDatabaseTest passed.");
    }
	
	@Test
	public void openLoyalSavingsInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Savings bankster = new Savings(profile, 500, 1);
        Assert.assertTrue(acctDB.open(bankster));
        System.out.println("openLoyalSavingsInDatabaseTest passed.");
    }
	
	@Test
	public void openDisloyalSavingsInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Savings bankster = new Savings(profile, 500, 0);
        Assert.assertTrue(acctDB.open(bankster));
        System.out.println("openDisloyalSavingsInDatabaseTest passed.");
    }
	@Test
	public void openMoneyMarketInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 2900);
        Assert.assertTrue(acctDB.open(bankster));
        System.out.println("openMoneyMarketInDatabaseTest passed.");
    }
	
	@Test
	public void openMoneyMarketUnder2500InDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 200);
        Assert.assertTrue(acctDB.open(bankster));
        System.out.println("openMoneyMarketUnder2500InDatabaseTest passed.");
    }
	
	@Test
    public void duplicateCheckingInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Checking bankster = new Checking(profile, 500);
        Assert.assertTrue(acctDB.open(bankster));
        Assert.assertFalse(acctDB.open(bankster));
        System.out.println("duplicateCheckingInDatabaseTest passed.");
    }
	
	@Test
    public void duplicateCollegeCheckingInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        CollegeChecking bankster = new CollegeChecking(profile, 500, 0);
        Assert.assertTrue(acctDB.open(bankster));
        Assert.assertFalse(acctDB.open(bankster));
        System.out.println("duplicateCollegeCheckingInDatabaseTest passed.");
    }
	
	@Test
	public void duplicateLoyalSavingsInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Savings bankster = new Savings(profile, 500, 1);
        Assert.assertTrue(acctDB.open(bankster));
        Assert.assertFalse(acctDB.open(bankster));
        System.out.println("duplicateLoyalSavingsInDatabaseTest passed.");
    }
	
	@Test
	public void duplicateDisloyalSavingsInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        Savings bankster = new Savings(profile, 500, 0);
        Assert.assertTrue(acctDB.open(bankster));
        Assert.assertFalse(acctDB.open(bankster));
        System.out.println("duplicateDisloyalSavingsInDatabaseTest passed.");
    }
	@Test
	public void duplicateMoneyMarketInDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 2900);
        Assert.assertTrue(acctDB.open(bankster));
        Assert.assertFalse(acctDB.open(bankster));
        System.out.println("duplicateMoneyMarketInDatabaseTest passed.");
    }
	
	@Test
	public void duplicateMoneyMarketUnder2500InDatabaseTest() {
		AccountDatabase acctDB = new AccountDatabase();
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 200);
        Assert.assertTrue(acctDB.open(bankster));
        Assert.assertFalse(acctDB.open(bankster));
        System.out.println("duplicateMoneyMarketUnder2500InDatabaseTest passed.");
    }
	

}
