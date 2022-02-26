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
            if(this.fname.equals(profileObj.fname) && this.lname.equals(profileObj.lname) && this.dob.equals(profileObj.dob)){
                return true;
            }
        }
        /*if (fname.equals(((Profile)obj).fname) && lname.equals(((Profile)obj).lname) && dob.equals(((Profile)obj).dob)){
            return true;
        }*/
        return false;
    }

    /**
     * This method overides the toString() method.
     * It returns a String representation of the Profile object in "DOB fname lname" format.
     */
    @Override
    public String toString() {
        String profileString = fname + " " + lname + " " + dob.toString();
        return profileString;
    }

}