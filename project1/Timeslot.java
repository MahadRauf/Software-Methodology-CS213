package project1;

/**
 * Object representation of a timeslot (date and time). Validity of date and time defined in their respective classes.
 * See Date and Time documentation for more details.
 * @author Mahad Rauf, Moeez Shahid
 */
public class Timeslot implements Comparable<Timeslot>{
    private Date date;
    private Time time;

    /**
     * 2 parameter constructor for Timeslot type.
     * @param date Date as a string in format MM/DD/YYYY
     * @param time Time in 24-hour as a string in format HH:MM
     */
    public Timeslot(String date, String time){
        this.date = new Date(date);
        String [] timeFormat = time.split(":");
        this.time = new Time(Integer.parseInt(timeFormat[0]), Integer.parseInt(timeFormat[1]));
    }

    /**
     * Coverts Timeslot to a String with format "date, time" or more specifically, "MM/DD/YYYY, HH:MM".
     * @overrride overrides toString() function.
     * @return Timeslot as a String. Formatted as: "MM/DD/YYYY, HH:MM".
     */
    public String toString(){
        String ret = this.date.toString() + ", " + this.time.toString();
        return ret;
    }

    /**
     * Compares two timeslots by first comparing the dates then the times.
     * @Override overrides compareTo() function
     * @param slot Timeslot to compare to.
     * @return 1 if greater than parameter timeslot, 0 if equal, -1 if less than
     */
    public int compareTo(Timeslot slot){
        int dateComparison = this.date.compareTo(slot.date);
        if(dateComparison > 0){
            return 1;
        }else if(dateComparison < 0){
            return -1;
        }else{
            int timeComparison = this.time.compareTo(slot.time);
            if(timeComparison > 0){
                return 1;
            }else if(timeComparison < 0){
                return -1;
            }else{
                return 0;
            }

        }
    }

    public Date getDate(){
        Date ret = this.date;
        return ret;
    }

    public Time getTime(){
        Time ret = this.time;
        return ret;
    }

    /**
     * Testbed main to test program. (in construction)
     * @param args command line input
     */
    public static void main(String [] args){
        //Test case #1: Same date but first timeslot is 45 minutes ahead
        Timeslot ts1 = new Timeslot("11/22/2020", "09:45");
        Timeslot ts2 = new Timeslot("11/22/2020", "09:00");
        Integer expectedResult = 1;
        Integer result =  ts1.compareTo(ts2);
        System.out.print("Test case #1: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }

        //Test case #2: Same date but first timeslot is 45 minutes behind
        ts1 = new Timeslot("11/22/2020", "08:15");
        ts2 = new Timeslot("11/22/2020", "09:00");
        expectedResult = -1;
        result =  ts1.compareTo(ts2);
        System.out.print("Test case #2: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }

        //Test case #3: Same date and same time
        ts1 = new Timeslot("11/22/2020", "09:00");
        ts2 = new Timeslot("11/22/2020", "09:00");
        expectedResult = 0;
        result =  ts1.compareTo(ts2);
        System.out.print("Test case #3: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }

        //Test case #4: Same time but first timeslot has a later date
        ts1 = new Timeslot("11/29/2020", "09:00");
        ts2 = new Timeslot("11/22/2020", "09:00");
        expectedResult = 1;
        result =  ts1.compareTo(ts2);
        System.out.print("Test case #4: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }

        //Test case #5: Same time but first timeslot has earlier date
        ts1 = new Timeslot("11/21/2020", "09:00");
        ts2 = new Timeslot("11/22/2020", "09:00");
        expectedResult = -1;
        result =  ts1.compareTo(ts2);
        System.out.print("Test case #5: ");
        if (result == expectedResult) {
            System.out.println("Pass.");
        }
        else {
            System.out.println("Fail.");
        }
    }
}
