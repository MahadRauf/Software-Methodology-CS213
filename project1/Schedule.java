package project1;

public class Schedule {
    private Appointment[] appointments;
    private int numAptts;
    private static final int NOT_FOUND = -1;
    private static final int ARRAY_GROWTH = 4;

    public Schedule() {
        this.appointments = new Appointment[4];
        this.numAptts = 0;
    }

    private int find(Appointment appt) {
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
        return true; // sorcery?
    }
}
