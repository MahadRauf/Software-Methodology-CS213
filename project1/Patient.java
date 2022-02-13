package project1;

/**
 * This class defines the Date abstract data type with year, month, and day.
 * @author Moeez Shahid, Mahad Rauf
 */

public class Patient implements Comparable<Patient>{
    private String fname;
    private String lname;
    private Date dob;

    /**
     * This is a parameterized constructor that takes a string in format of "DOB fname lname"
     * as an input and returns an instant of patient.
     * fname is first name and lname is last name.
     * @param patient is a patient in the form of "DOB fname lname"
     */
    public Patient(String patient) {
        String[] patientFormat = patient.split(" ");
        dob = new Date(patientFormat[0]);
        fname = patientFormat[1];
        lname = patientFormat[2];
    }

    /**
     * This method overides the toString() method.
     * It returns a String representation of the Patient object in "DOB fname lname" format.
     */
    @Override
    public String toString() {
        String patientString = dob.toString() + " " + fname + " " + lname;
        return patientString;
    }

    /**
     * This method overrides the compareTo() method and compares 2 patients.
     * compares last names, if equal moves onto first names.
     * if first names are equal, compare DOB.
     * @return 1 if greater than, 0 if equal, and -1 if less than.
     */
    @Override
    public int compareTo(Patient patient) {
        /*int compResultLast = compareLName(patient);
        if(compResultLast == 1) {
            return 1;
        }
        else if(compResultLast == -1) {
            return -1;
        }
        else {
            int compResultFirst = compareFName(patient);
            if(compResultFirst == 1) {
                return 1;
            }
            if(compResultFirst == -1) {
                return -1;
            }
            else {
                int compResultDOB = compareDOB(patient);
                if(compResultDOB == 1) {
                    return 1;
                }
                if(compResultDOB == -1) {
                    return -1;
                }
            }
        }
        return 0;*/
        if(this.lname.compareTo(patient.lname) > 0){
            return 1;
        }else if(this.lname.compareTo(patient.lname) < 0){
            return -1;
        }else{
            if(this.fname.compareTo(patient.fname) > 0){
                return 1;
            }else if(this.fname.compareTo(patient.fname) < 0){
                return -1;
            }else{
                if(this.dob.compareTo(patient.dob) == 1){
                    return 1;
                }else if(this.dob.compareTo(patient.dob) == -1){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
    }


    /**
     * This method compares the last names of patients.
     * @param patient's last name is being compared to that of the object.
     * @return 1 if greater than, -1 if less than, 0 if equal.
     */
    private int compareLName(Patient patient) {
        int ascii1[] = new int[lname.length()];
        for(int i = 0; i < lname.length(); i++) {
            ascii1[i] = lname.charAt(i);
        }
        int ascii2[] = asciiLastName(patient);
        int compResult = arrayCompare(ascii1, ascii2);
        if(compResult == 1) {
            return 1;
        }
        if(compResult == -1) {
            return -1;
        }
        return 0;
    }

    /**
     * This method compares the first names of patients.
     * @param patient's first name is being compared to that of the object.
     * @return 1 if greater than, -1 if less than, 0 if equal.
     */
    private int compareFName(Patient patient) {
        int ascii1[] = new int[fname.length()];
        for(int i = 0; i < fname.length(); i++) {
            ascii1[i] = fname.charAt(i);
        }
        int ascii2[] = asciiFirstName(patient);
        int compResult = arrayCompare(ascii1, ascii2);
        if(compResult == 1) {
            return 1;
        }
        if(compResult == -1) {
            return -1;
        }
        return 0;
    }

    /**
     * This method compares the dates of birth of patients.
     * it is using the compareTo function of Date.
     * @param patient's date of birth is being compared to that of the object.
     * @return 1 if greater than, -1 if less than, 0 if equal.
     */
    private int compareDOB(Patient patient) {
        int compResult = dob.compareTo(patient.dob);
        return compResult;
    }

    /**
     * This function turns a string into an array of numbers.
     * each number represents the ascii numbers of the characters in the original string.
     * this method is specifically for the last name.
     * @param patient has its last name turned into an array of numbers.
     * @return the array of numbers.
     */
    private int[] asciiLastName(Patient patient) {
        int asciiArray[] = new int[patient.lname.length()];
        for(int i = 0; i < patient.lname.length(); i++) {
            asciiArray[i] = patient.lname.charAt(i);
        }
        return asciiArray;
    }

    /**
     * This function turns a string into an array of numbers.
     * each number represents the ascii numbers of the characters in the original string.
     * this method is specifically for the last name.
     * @param patient has its last name turned into an array of numbers.
     * @return the array of numbers.
     */
    private int[] asciiFirstName(Patient patient) {
        int asciiArray[] = new int[patient.fname.length()];
        for(int i = 0; i < patient.fname.length(); i++) {
            asciiArray[i] = patient.fname.charAt(i);
        }
        return asciiArray;
    }
    /**
     * This function compares two integer arrays and checks which one is greater.
     * if one array is shorter than the other, but are equal until that point,
     * the shorter one is considered to be greater.
     * @param array1
     * @param array2
     * @return 1 if greater than, -1 if less than, 0 if equal.
     */
    private int arrayCompare(int array1[], int array2[]) {
        int minArrLength;
        if(array1.length  > array2.length) {
            minArrLength = array2.length;
        }
        else {
            minArrLength = array1.length;
        }
        for(int i = 0; i < minArrLength; i++) {
            if(array1[i] != array2[i]) {
                if(array1[i] > array2[i]) {
                    return 1;
                }
                else
                    return -1;
            }
        }
        if(minArrLength == array1.length && minArrLength == array2.length) {
            return 0;
        }
        if(array1.length > array2.length) {
            return -1;
        }
        return 1;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stu

    }

}
