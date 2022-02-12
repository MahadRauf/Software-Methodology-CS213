package project1;

public class Schedule {
    private Appointment[] appointments;
    private int numAptts;
    private static final int NOT_FOUND = -1;
    private static final int ARRAY_GROWTH = 4;
    private static final int INITIAL_SIZE = 4;

    public Schedule() {
        this.appointments = new Appointment[INITIAL_SIZE];
        this.numAptts = 0;
    }

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

    private void grow() {
        int newSize = this.appointments.length + ARRAY_GROWTH;
        Appointment[] newAppArray = new Appointment[newSize];
        for (int i = 0; i < numAptts; i++) {
            newAppArray[i] = this.appointments[i];
        }
        this.appointments = newAppArray;
    }

    public boolean add(Appointment appt) {
        if(appt == null){
            return false;
        }
        if (find(appt) != NOT_FOUND) {
            return false; // is this magic number?
        }
        if (numAptts == this.appointments.length) {
            this.grow();
        }
        this.appointments[numAptts] = appt;
        numAptts++;
        return true; // magic beans?
    }

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
        for (int i = 0; i < this.appointments.length; i++) {
            newAppArray[i] = this.appointments[i + 1];
        }
        numAptts--;
        return true; // sorcery?
    }

    public void print() {
        for (int i = 0; i < this.appointments.length; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

    public void printByZip(){
        for(int i = 0; i < this.appointments.length - 1; i++){
            int minIdx = i;
            int minZip = this.appointments[minIdx].getLocation().ZIP;
            for(int j = i + 1; j < this.appointments.length; j++){
                int jZip = this.appointments[j].getLocation().ZIP;
                if(minZip > jZip){
                    minIdx = j;
                }else if(minZip == jZip){
                    Timeslot minSlot = this.appointments[minIdx].getSlot();
                    Timeslot jSlot = this.appointments[j].getSlot();
                    if(minSlot.compareTo(jSlot) == 1){
                        minIdx = j;
                    }
                }
            }
            Appointment temp = this.appointments[minIdx];
            this.appointments[minIdx] = this.appointments[i];
            this.appointments[i] = temp;
        }
        for(int i = 0; i < this.appointments.length; i++){
            System.out.println(this.appointments[i].toString()); //fix toString w\ proper format
        }
    }

    public void printByPatient(){
        for(int i = 0; i < this.appointments.length - 1; i++){
            int minIdx = i;
            Patient minPat = this.appointments[minIdx].getPatient();
            for(int j = i + 1; j < this.appointments.length; j++){
                Patient jPat = this.appointments[j].getPatient();
                if(minPat.compareTo(jPat) == 1){
                    minIdx = j;
                }
            }
            Appointment temp = this.appointments[minIdx];
            this.appointments[minIdx] = this.appointments[i];
            this.appointments[i] = temp;
        }
        print();
    }
}
