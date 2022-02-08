
package project1;

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
    
    
	
	private int year;
    private int month;
    private int day;
	
    public Date(String date) {
        String[] dateFormat = date.split("/");
        month = Integer.parseInt(dateFormat[0]);
        day = Integer.parseInt(dateFormat[1]);
        year = Integer.parseInt(dateFormat[2]);
    }
    
	public Date() {
        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
    } 
	
	
	/**
     * This method overrides the compareTo() method and compares 2 dates.
     * @return integer representing the difference in the number of days between 2 dates.
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
	
	private boolean leapFebruary() {
		if(month == FEBRUARY) {
			if(isLeapYear(year)) {
				return true;
			}
		}
		return false;
	}
	
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
	
	private boolean isFutureDate() {
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
     * Returns a String representation of the Date object in "mm/dd/yyyy" format.
     */
    @Override
    public String toString() {
        String dateString = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        return dateString;
    }
	
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
	
	
	public static void main(String[] args) {
		
		

	}
	
	

}
