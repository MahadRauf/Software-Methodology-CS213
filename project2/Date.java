package project2;

import java.util.Calendar;

/**
 * This class defines the Date abstract data type with year, month, and day.
 * @author Moeez Shahid, Mahad Rauf
 */
public class Date implements Comparable<Date> {

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL  = 400;
    public static final int FebruaryNormal = 28;
    public static final int FebruaryLeap = 29;
    public static final int JMMJAOD = 31;//January March May July August October December
    public static final int AJSN = 30; //April June September November
    public static final int minimumDays = 1;
    public static final int daysInYear = 365;
    public static final int lastCentury = 1900;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    /** year of the DOB */
    private int year;
    /** month of the DOB */
    private int month;
    /** day of the DOB */
    private int day;

    /**
     * This is a parameterized constructor that takes a String in the form of "MM/DD/YYYY" as an input and returns an instance of Date.
     * @param date is a date in the form of "MM/DD/YYYY".
     */
    public Date(String date) {
        String[] dateFormat = date.split("/");
        month = Integer.parseInt(dateFormat[0]);
        day = Integer.parseInt(dateFormat[1]);
        year = Integer.parseInt(dateFormat[2]);
    }

    /**
     * This constructor returns today's date.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
    }


    /**
     * This method overrides the compareTo() method and compares 2 dates.
     * @return 1 if greater than, 0 if equal, and -1 if less than.
     */
    @Override
    public int compareTo(Date date) {
        int yearDifference = year - date.year;
        int monthDifference = month - date.month;
        int dayDifference = day - date.day;
        int sum = (yearDifference * daysInYear) + (monthDifference * AJSN) + dayDifference;
        if(sum > 0) {
            return 1;
        }
        if(sum < 0) {
            return -1;
        }
        return 0;
    }


    /**
     * This method checks whether the input year is a leap year or not.
     * @param year is the year that is going to be checked.
     * @return true if input year is a leap year, otherwise return false.
     */
    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    return true;
                }
            }
            else
                return true;
        }
        return false;
    }

    /**
     * This method checks if a date with a month of February is a leap year or not.
     * @return true if it is a February in a leap year, otherwise return false.
     */
    private boolean leapFebruary() {
        if(month == FEBRUARY) {
            if(isLeapYear(year)) {
                return true;
            }
        }
        return false;
    }
    /**
     * This method checks if a date with a month of February is a valid calendar date.
     * @return true if date in February is valid, otherwise return false.
     */
    private boolean validFebruary() {
        if(leapFebruary()) {
            if(day >= minimumDays && day <= FebruaryLeap) {
                return true;
            }
        }
        else if(!leapFebruary() && month == FEBRUARY) {
            if(day >= minimumDays && day <= FebruaryNormal) {
                return true;
            }
        }

        return false;
    }
    /**
     * This method checks if a date is in the future.
     * @return true if it is a date in the future, false otherwise.
     */
    public boolean isFutureDate() {
        Calendar calendar = Calendar.getInstance();
        if(year > calendar.get(Calendar.YEAR)) {
            return true;
        }
        else if (year == calendar.get(Calendar.YEAR)) {
            if (month > calendar.get(Calendar.MONTH))
                return true;
            else if (month == calendar.get(Calendar.MONTH)) {
                if (day > calendar.get(Calendar.DATE))
                    return true;
            }
        }
        return false;
    }

    /**
     * This method checks if a date is today
     * @return true if input is current day, false otherwise
     */
    public boolean isToday() {
        Calendar calendar = Calendar.getInstance();
        if(year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) && day == calendar.get(Calendar.DATE)) {
            return true;
        }
        return false;
    }


    /**
     * This method overrides the toString() method.
     * Returns a String representation of the Date object in "mm/dd/yyyy" format.
     */
    @Override
    public String toString() {
        String dateString = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        return dateString;
    }

    /**
     * This method checks whether an instance of Date is a valid date or not.
     * A Date is considered valid if it corresponds to a valid calendar date.
     * @return true if Date is a valid date, otherwise return false otherwise.
     */
    public boolean isValid() {
        Calendar calendar = Calendar.getInstance();
        if(month < JANUARY || month > DECEMBER || day < minimumDays || day > JMMJAOD) {
            return false;
        }
        if(month == JANUARY || month == MARCH || month == MAY || month == JULY || month == AUGUST || month == OCTOBER
                || month == DECEMBER) {
            return true;
        }
        if(month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            if(day > AJSN) {
                return false;
            }
        }
        if(month == FEBRUARY) {
            if(validFebruary()) {
                return true;
            }
            if(!validFebruary()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Testbed main to test program, Date.
     * @param args command line input
     */
    public static void main(String[] args) {
        //Test cases for isValid()
        boolean expectedResult = false;
        boolean result;

        // Test case #1: A date with a valid day and year, but a month above 12
        Date date = new Date("13/13/2003");
        result = date.isValid();
        System.out.print("Test case #1: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }
        // Test case #2: A date with a valid day and year, but a month below 1
        date = new Date("0/14/2005");
        result = date.isValid();
        System.out.print("Test case #2: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }
        // Test case #3: A date with a 31-day month and valid year, but invalid day
        date = new Date("10/34/2009");
        result = date.isValid();
        System.out.print("Test case #3: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }
        // Test case #4: A date with a 30-day month and valid year, but invalid day
        date = new Date("4/31/2007");
        result = date.isValid();
        System.out.print("Test case #4: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }

        // Test case #5: A date with a valid month and year, but a negative day
        date = new Date("5/-6/1975");
        result = date.isValid();
        System.out.print("Test case #5: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }
        // Test case #6: A date with February as its month and valid non-leap year, but invalid day of 29
        date = new Date("2/29/2001");
        result = date.isValid();
        System.out.print("Test case #6: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }
        // Test case #7: A date with February as its month and valid leap year, but invalid day of 30
        date = new Date("2/30/2000");
        result = date.isValid();
        System.out.print("Test case #7: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }
    }
}