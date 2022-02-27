package project2;

import org.junit.Assert;
import org.junit.Test;

public class DateJUnitTest {
	
	@Test
    public void validDayTest() {
        Assert.assertTrue(new Date("01/1/2008").isValid());
        Assert.assertTrue(new Date("02/10/2008").isValid());
        Assert.assertTrue(new Date("03/30/2008").isValid());
        System.out.println("validDayTest passed.");
    }
	
	@Test
    public void invalidDayTest() {
        Assert.assertFalse(new Date("01/-1/2008").isValid());
        Assert.assertFalse(new Date("02/00/2008").isValid());
        Assert.assertFalse(new Date("03/32/2008").isValid());
        System.out.println("invalidDayTest passed.");
    }
	
    @Test
    public void validYearTest() {
        Assert.assertTrue(new Date("01/12/2022").isValid());
        Assert.assertTrue(new Date("04/19/2020").isValid());
        System.out.println("validYearTest passed.");
    }
    
    
    @Test
    public void validMonthTest() {
        Assert.assertTrue(new Date("12/06/2019").isValid());
        Assert.assertTrue(new Date("01/17/2008").isValid());
        Assert.assertTrue(new Date("03/12/2008").isValid());
        System.out.println("validMonthTest passed.");
    }

    @Test
    public void invalidMonthTest() {
        Assert.assertFalse(new Date("14/06/2019").isValid());
        Assert.assertFalse(new Date("00/17/2008").isValid());
        Assert.assertFalse(new Date("-3/12/2008").isValid());
        System.out.println("invalidMonthTest passed.");
    }
    
    @Test
    public void validFebruaryLeapYearTest() {
        Assert.assertTrue(new Date("02/28/2020").isValid());
        Assert.assertTrue(new Date("02/28/2000").isValid());
        System.out.println("validFebruaryLeapYearTest passed.");
    }

    @Test
    public void invalidFebruaryLeapYearTest() {
        Assert.assertFalse(new Date("02/30/2020").isValid());
        Assert.assertFalse(new Date("02/29/2021").isValid());
        System.out.println("invalidFebruaryLeapYearTest passed.");
    }


    @Test
    public void validThirtyOneDayMonthTest() {
        Assert.assertTrue(new Date("1/31/2008").isValid());
        Assert.assertTrue(new Date("3/31/2008").isValid());
        Assert.assertTrue(new Date("5/31/2008").isValid());
        Assert.assertTrue(new Date("7/31/2008").isValid());
        Assert.assertTrue(new Date("8/31/2008").isValid());
        System.out.println("validThirtyOneDayMonthTest passed.");
    }
    
    @Test
    public void invalidThirtyOneDayMonthTest() {
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("1/32/2008").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("3/32/2008").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("5/32/2008").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("7/32/2008").isValid());
        Assert.assertFalse("thirtyOneDayMonthFalseTest failed", new Date("8/32/2008").isValid());
        System.out.println("invalidThirtyOneDayMonthTest passed.");
    }

    @Test
    public void validThirtyDayMonthTest() {
        Assert.assertTrue(new Date("4/30/2008").isValid());
        Assert.assertTrue(new Date("6/30/2008").isValid());
        System.out.println("validThirtyDayMonthTest passed.");
    }
    
    @Test
    public void invalidThirtyDayMonthTest() {
        Assert.assertFalse(new Date("4/31/2008").isValid());
        Assert.assertFalse(new Date("6/31/2008").isValid());
        Assert.assertFalse(new Date("9/31/2008").isValid());
        System.out.println("invalidThirtyDayMonthTest passed.");
    }
}
