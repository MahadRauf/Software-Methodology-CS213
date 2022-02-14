package project1;

/**
 * Object representation of time in 24-hour format, not all time valid see isValid documentation for more information.
 * @author Mahad Rauf, Moeez Shahid
 */
public class Time implements Comparable<Time>{
    private int hour;
    private int minute;
    public static final int MIN_HOUR = 9;
    public static final int MAX_HOUR = 16;
    public static final int MIN_MINUTE = 0;
    public static final int MAX_MINUTE = 45;

    /**
     * Default constructor for Time type. Sets time to 9:00.
     */
    public Time(){
        this.hour = 9;
        this.minute = 0;
    }

    /**
     * 2 parameter constructor for Time type. Hour input less than 9 or greater will be deemed invalid. Same for minute
     * input that is not a multiple of 15 and not between 0-45.
     * @param hour the hour of the time in 24-hour format.
     * @param minute the minute of the time.
     */
    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Determines whether the time is valid. That being the time is in between 09:00 and 16:45 and the minute is either
     * 0, 15, 30, or 45.
     * @return boolean true or false whether time is valid
     */
    public boolean isValid(){
        boolean retVal = false;
        if (this.hour <= MAX_HOUR && this.hour >= MIN_HOUR && this.minute <= MAX_MINUTE && this.minute >= MIN_MINUTE && this.minute % 15 == 0) {
            retVal = true;
        }
        return retVal;
    }

    /**
     * Overrides the toString method with one appropriate for the class. Overrides toString().
     * @return returns the time as a String in HH:mm format.
     */
    @Override
    public String toString(){
        String retString = String.format("%02d:%02d", this.hour, this.minute);
        return retString;
    }

    /**
     * Overrides the compareTo method with one appropriate for the class. Overrides compareTo().
     * @param time the other time object to compare with
     * @return 1 if greater than parameter time, 0 if equal, -1 if less than
     */
    @Override
    public int compareTo(Time time){
        int retVal = 0;
        if(this.hour > time.hour){
            retVal = 1;
        }else if(this.hour < time.hour){
            retVal = -1;
        }else{
            if(this.minute > time.minute){
                retVal = 1;
            }else if(this.minute < time.minute){
                retVal = -1;
            }else{
                retVal = 0;
            }
        }
        return retVal;
    }
}
