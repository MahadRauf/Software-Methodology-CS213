package project1;

/**
 * Implementation of an appointment schedule at a clinic as an array of Appointment objects that grows as
 * the number of appointments scheduled increases.
 * @author Mahad Rauf, Moeez Shahid
 */
public class Schedule {
    private Appointment[] appointments;
    private int numAptts;
    private static final int NOT_FOUND = -1;
    private static final int ARRAY_GROWTH = 4;
    private static final int INITIAL_SIZE = 4;

    /**
     * Constructor for Schedule class. Creates Appointment array of initial size 4 and sets the number of
     * appointments to 0.
     */
    public Schedule() {
        this.appointments = new Appointment[INITIAL_SIZE];
        this.numAptts = 0;
    }

    /**
     * Goes through the schedule to find an appointment with the same patient as the one in the parameter appointment.
     * @param appt appointment that has the patient that we want to find more appointments in the schedule with that patient.
     * @return The appointment that has the same patient as the parameter appointment, null if there are no other appointments with that patient.
     */
    public Appointment findPatient(Appointment appt){
        Patient findPtnt = appt.getPatient();
        for (int i = 0; i < this.numAptts; i++) {
            Patient tempPtnt = this.appointments[i].getPatient();
            if (findPtnt.compareTo(tempPtnt) == 0) {
                Appointment ret = this.appointments[i];
                return ret;
            }
        }
        return null;
    }

    /**
     * Checks the schedule to see if a patient already has an appointment at some location at the same date.
     * @param appt appointment being compared to those already in the schedule.
     * @return True if found in schedule, false otherwise
     */
    public boolean hasSimilarAppt(Appointment appt){
        Date apptDate = appt.getSlot().getDate();
        Patient apptPtnt = appt.getPatient();
        for (int i = 0; i < numAptts; i++) {
            Date tempDate = this.appointments[i].getSlot().getDate();
            Patient tempPtnt = this.appointments[i].getPatient();
            if (apptDate.compareTo(tempDate) == 0 && apptPtnt.compareTo(tempPtnt) == 0) {
                return true;
            }
        }
        return false;

    }

    /**
     * Checks the schedule to see if the timeslot at a specific location is already booked for an appointment.
     * @param appt appointment being compared to those already in the schedule.
     * @return True if found in schedule, false otherwise
     */
    public boolean slotTaken(Appointment appt){
        Timeslot apptSlot = appt.getSlot();
        Location apptLoc = appt.getLocation();
        for (int i = 0; i < numAptts; i++) {
            Timeslot tempSlot = this.appointments[i].getSlot();
            Location tempLoc = this.appointments[i].getLocation();
            if (apptSlot.compareTo(tempSlot) == 0 && apptLoc.equals(tempLoc)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the schedule to see if the exact same appointment already exists in the schedule.
     * @param appt appointment being compared to those already in the schedule.
     * @return True if found in schedule, false otherwise
     */
    public boolean apptExists(Appointment appt){
        if(this.find(appt) != NOT_FOUND){
            return true;
        }
        return false;
    }

    /**
     * Checks the schedule for an appointment and returns its index of it exists in the schedule.
     * @param appt appointment being compared to those already in the schedule.
     * @return the index if the appointment in the array if it exists in it, -1 otherwise.
     */
    private int find(Appointment appt) {
        if(appt == null){
            return NOT_FOUND;
        }
        for (int i = 0; i < numAptts; i++) {
            if (this.appointments[i].equals(appt)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Increases the size of the schedule array by 4.
     */
    private void grow() {
        int newSize = this.appointments.length + ARRAY_GROWTH;
        Appointment[] newAppArray = new Appointment[newSize];
        for (int i = 0; i < numAptts; i++) {
            newAppArray[i] = this.appointments[i];
        }
        this.appointments = newAppArray;
    }

    /**
     * Adds parameter appointment into the schedule array. Does not add to schedule if appointment already exists
     * or is null.
     * @param appt appointment to add to schedule array.
     * @return true if it was successfully added, false otherwise.
     */
    public boolean add(Appointment appt) {
        if(appt == null){
            return false;
        }
        if (find(appt) != NOT_FOUND) {
            return false;
        }
        if (this.numAptts == this.appointments.length) {
            this.grow();
        }
        this.appointments[this.numAptts] = appt;
        this.numAptts++;
        return true;
    }

    /**
     * Removes the appointment matching the parameter appointment from the schedule.
     * @param appt appointment to remove from schedule array
     * @return true if successfully removed, false if parameter os null or appointment not in schedule
     */
    public boolean remove(Appointment appt) {
        if(appt == null){
            return false;
        }
        int delIndex = find(appt);
        if (delIndex == NOT_FOUND) {
            return false; // witchcraft?
        }
        Appointment[] newAppArray = new Appointment[this.appointments.length];
        for (int i = 0; i < delIndex; i++) {
            newAppArray[i] = this.appointments[i];
        }
        for (int i = delIndex; i < this.appointments.length - 1; i++) {
            newAppArray[i] = this.appointments[i + 1];
        }
        this.numAptts--;
        this.appointments = newAppArray;
        return true; // sorcery?
    }

    /**
     * prints the schedule array in the order that it is currently in.
     */
    public void print() {
        System.out.println();
        System.out.println("*list of appointments in the schedule*");
        for (int i = 0; i < numAptts; i++) {
            System.out.println(this.appointments[i].toString());
        }
        System.out.println("*end of schedule*");
        System.out.println();
    }

    /**
     * prints the schedule array ordered by the zip code of the locations the appointment is.
     */
    public void printByZip(){
        for(int i = 0; i < numAptts - 1; i++){
            int minIdx = i;
            int minZip = this.appointments[minIdx].getLocation().ZIP;
            for(int j = i + 1; j < numAptts; j++){
                int jZip = this.appointments[j].getLocation().ZIP;
                if(minZip > jZip){
                    minIdx = j;
                    minZip = this.appointments[minIdx].getLocation().ZIP;
                }else if(minZip == jZip){
                    Timeslot minSlot = this.appointments[minIdx].getSlot();
                    Timeslot jSlot = this.appointments[j].getSlot();
                    if(minSlot.compareTo(jSlot) == 1){
                        minIdx = j;
                        minZip = this.appointments[minIdx].getLocation().ZIP;
                    }
                }
            }
            Appointment temp = this.appointments[minIdx];
            this.appointments[minIdx] = this.appointments[i];
            this.appointments[i] = temp;
        }
        System.out.println();
        System.out.println("*list of appointments by zip and time slot*");
        for(int i = 0; i < numAptts; i++){
            System.out.println(this.appointments[i].toString()); //fix toString w\ proper format
        }
        System.out.println("*end of schedule*");
        System.out.println();
    }

    /**
     * prints the schedule array ordered by the patients' (last name, first name, dob) in that order of importance.
     */
    public void printByPatient(){
        for(int i = 0; i < numAptts - 1; i++){
            int minIdx = i;
            Patient minPat = this.appointments[minIdx].getPatient();
            for(int j = i + 1; j < numAptts; j++){
                Patient jPat = this.appointments[j].getPatient();
                if(minPat.compareTo(jPat) == 1){
                    minIdx = j;
                    minPat = this.appointments[minIdx].getPatient();;
                }
            }
            Appointment temp = this.appointments[minIdx];
            this.appointments[minIdx] = this.appointments[i];
            this.appointments[i] = temp;
        }
        System.out.println();
        System.out.println("*list of appointments by patient*");
        for(int i = 0; i < numAptts; i++){
            System.out.println(this.appointments[i].toString()); //fix toString w\ proper format
        }
        System.out.println("*end of schedule*");
        System.out.println();
    }
}