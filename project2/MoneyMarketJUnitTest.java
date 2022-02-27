package project2;

import org.junit.Assert;
import org.junit.Test;

public class MoneyMarketJUnitTest {
	
	 private static final double monthlyInterest = 0.8;
	 private static final double loyalMonthlyInterest = 0.95;
	
	@Test
    public void moreThan3WithdrawsTest() {
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 7000);
        bankster.withdraw(20);
        bankster.withdraw(20);
        bankster.withdraw(20);
        bankster.withdraw(20);
        Assert.assertEquals(0, bankster.monthlyInterest(), monthlyInterest);
        System.out.println("moreThan3WithdrawsTest Succesful");
    }
	
	@Test
	public void lessThan3WithdrawsTest() {
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 7000);
        bankster.withdraw(20);
        bankster.withdraw(20);
        bankster.withdraw(20);
        Assert.assertEquals(0, bankster.monthlyInterest(), loyalMonthlyInterest);
        System.out.println("lessThan3WithdrawsTest Succesful");
    }
	
	@Test
	public void dipBelow2500Test() {
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 2600);
        bankster.withdraw(200);
        bankster.withdraw(200);
        Assert.assertEquals(0, bankster.monthlyInterest(), monthlyInterest);
        System.out.println("dipBelow2500Test Succesful");
    }
	
	@Test
	public void dipBelow2500AndBackTest() {
		Profile profile = new Profile("Moeez", "Shahid", new Date("02/10/2001"));
        MoneyMarket bankster = new MoneyMarket(profile, 2600);
        bankster.withdraw(200);
        bankster.withdraw(200);
        bankster.deposit(500);
        Assert.assertEquals(0, bankster.monthlyInterest(), monthlyInterest);
        System.out.println("dipBelow2500AndBackTest Succesful");
    }
}
