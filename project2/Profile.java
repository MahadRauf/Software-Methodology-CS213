package project2;

/**
 * This class defines the Profile abstract data type with fname, lname, and dob.
 * @author Moeez Shahid, Mahad Rauf
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * constructor for Profile type
     * @param fname first name of the person
     * @param lname last name of the person
     * @param dob date of birth of the person in (MM/DD/YYYY)
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Determines if two 'Profile' instances are equal to each other or not by comparing their names and dob.
     * @param obj 'Profile' object to be compared with.
     * @return true if the 'Profile' instances are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Profile){
            Profile profileObj = (Profile) obj;
            String fnameThis = this.fname.toLowerCase();
            String lnameThis = this.lname.toLowerCase();
            String fnameObj = profileObj.fname.toLowerCase();
            String lnameObj = profileObj.lname.toLowerCase();
            if(fnameThis.equals(fnameObj) && lnameThis.equals(lnameObj) && this.dob.compareTo(profileObj.dob) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * This method overrides the toString() method.
     * It returns a String representation of the Profile object in "DOB fname lname" format.
     */
    @Override
    public String toString() {
        String profileString = this.fname + " " + this.lname + " " + this.dob.toString();
        return profileString;
    }

    /**
     * gets the last name from the profile
     * @return last name of the profile
     */
    public String getLname(){
        String ret = this.lname;
        return ret;
    }

    /**
     * gets the first name of the profile
     * @return first name of the profile
     */
    public String getFname(){
        String ret = this.fname;
        return ret;
    }

    /**
     * gets the date of birth of the profile
     * @return date of birth of the profile
     */
    public Date getDOB(){
        Date ret = this.dob;
        return ret;
    }

}