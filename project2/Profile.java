package project2;

/**
 * This class defines the Profile abstract data type with fname, lname, and dob.
 * @author Moeez Shahid, Mahad Rauf
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;


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
     * This method overides the toString() method.
     * It returns a String representation of the Profile object in "DOB fname lname" format.
     */
    @Override
    public String toString() {
        String profileString = this.fname + " " + this.lname + " " + this.dob.toString();
        return profileString;
    }

    public String getLname(){
        String ret = this.lname;
        return ret;
    }

    public String getFname(){
        String ret = this.fname;
        return ret;
    }

    public Date getDOB(){
        Date ret = this.dob;
        return ret;
    }

}