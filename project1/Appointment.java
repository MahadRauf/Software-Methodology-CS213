package project1;

/**
 * Object representation of an appointment which has fields patient, slot, and location that help identify it.
 * @author Mahad Rauf, Moeez Shahid
 */
public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    /**
     * Parametrized constructor for Appointment type. Parameters are strings patient, date, time, and location.
     * @param patient string in format "MM/DD/YYYY firstname lastname"
     * @param date string in format "MM/DD/YYYY"
     * @param time string in format "HH:MM"
     * @param location string that is one of: "SOMERSET", "MIDDLESEX", "MERCER", "MORRIS", "UNION"
     */
    public Appointment(String patient, String date, String time, String location){
        this.patient = new Patient(patient);
        this.slot = new Timeslot(date, time);
        this.location = Location.valueOf(location);
    }

    /**
     * Method helping in implementation of equals(). Checks each part of appointment for equality.
     * @param appt appointment to be compared with.
     * @return false if appointments not equal, true otherwise.
     */
    private boolean apptEquals(Appointment appt){
        boolean retBool = false;
        if(this.patient.compareTo(appt.patient) == 0 && this.slot.compareTo(appt.slot) == 0 && this.location.equals(appt.location)){
            retBool = true;
        }
        return retBool;
    }
    /**
     * Overrides equals(Object obj). Checks if parameter object is equal to specified appointment.
     * @return false if object is not of type Appointment or not equal, true otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        boolean ret = false;
        if(obj instanceof Appointment){
            Appointment appObj = (Appointment) obj;
            ret = apptEquals(appObj);
        }
        return ret;
    }

    /**
     * Overrides toString(). Converts Appointment type into string representation.
     * @return String representation of appointment in format "patient, Appointment detail: slot, location".
     */
    @Override
    public String toString(){
        String ret = this.patient.toString() + ", Appointment detail: " + this.slot.toString() + ", " + this.location.toString();
        return ret;
    }

    /**
     * Getter method that returns patient field of Appointment.
     * @return patient field of Appointment.
     */
    public Patient getPatient(){
        Patient ret = this.patient;
        return ret;
    }

    /**
     * Getter method that returns slot field of Appointment.
     * @return slot field of Appointment.
     */
    public Timeslot getSlot(){
        Timeslot ret = this.slot;
        return ret;
    }

    /**
     * Getter method that returns location field of Appointment.
     * @return patient field of Appointment.
     */
    public Location getLocation(){
        Location ret = this.location;
        return ret;
    }
}
