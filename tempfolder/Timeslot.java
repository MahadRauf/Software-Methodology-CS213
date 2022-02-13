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
        Timeslot ts = new Timeslot("11/22/2022", "09:45");
        System.out.println(ts.toString());
    }
}
