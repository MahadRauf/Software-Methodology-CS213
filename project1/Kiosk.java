package project1;

import java.util.Scanner;

public class Kiosk {
    private static final String BOOK = "B";
    private static final String CANCEL = "C";
    private static final String CANCEL_PATIENT_ALL = "CP";
    private static final String DISPLAY_ALL = "P";
    private static final String DISPLAY_ALL_BY_ZIP = "PZ";
    private static final String DISPLAY_ALL_BY_PTNT = "PP";
    private static final String QUIT = "Q";

    public boolean locationExists(String location){
        for(Location loc : Location.values()){
            if(loc.name().equals(location)){
                return true;
            }
        }
        return false;
    }

    private void bookAppt(String [] command, Schedule schedule){
        String patientString = command[1] + " " + command[2] + " " + command[3];
        Date apptDate = new Date(command[4]);
        Date birthDay = new Date(command[1]);
        if(!locationExists(command[6].toUpperCase())){
            System.out.println("Invalid location!");
            return;
        }
        Appointment apptToAdd = new Appointment(patientString, command[4], command[5], command[6].toUpperCase());
        if(birthDay.isFutureDate() || birthDay.isToday()){
            System.out.println("Date of birth invalid -> it is a future date");
            return;
        }
        if(!birthDay.isValid()){
            System.out.println("Invalid date of birth!");
            return;
        }
        if(!apptDate.isValid() || apptDate.isFutureYear()){
            System.out.println("Invalid appointment date!");
            return;
        }
        if(apptDate.isPastDate() || apptDate.isToday()){
            System.out.println("Appointment date invalid -> must be a future date");
            return;
        }
        String [] timeParam = command[5].split(":");
        Time apptTime = new Time(Integer.parseInt(timeParam[0]), Integer.parseInt(timeParam[1]));
        if(!apptTime.isValid()){
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
            return;
        }
        if(schedule.apptExists(apptToAdd)){
            System.out.println("Same appointment exists in the schedule.");
            return;
        }
        if(schedule.slotTaken(apptToAdd)){
            System.out.println("Time slot has been taken at this location.");
            return;
        }
        if(schedule.hasSimilarAppt(apptToAdd)){
            System.out.println("Same patient cannot book an appointment with the same date.");
            return;
        }
        schedule.add(apptToAdd);
        System.out.println("Appointment booked and added to the schedule.");
    }

    private void cancelAppt(String [] command, Schedule schedule){
        String patientString = command[1] + " " + command[2] + " " + command[3];
        Date apptDate = new Date(command[4]);
        Date birthDay = new Date(command[1]);
        String [] timeParam = command[5].split(":");
        Time apptTime = new Time(Integer.parseInt(timeParam[0]), Integer.parseInt(timeParam[1]));


        Appointment apptToDelete = new Appointment(patientString, command[4], command[5], command[6].toUpperCase());
        if(schedule.remove(apptToDelete)) {
            System.out.println("Appointment cancelled");
        }
        else {
            System.out.println("Not cancelled, appointment does not exist.");
        }

    }

    // take a whatever appointment and send that to some method so we have the patient and then delete all appt with same patient (implement)
    private void cancelAllAppt(String [] command, Schedule schedule){
        String patientString = command[1] + " " + command[2] + " " + command[3];
        // appointment given filler values so patient can be sent along with it.
        Appointment simApptToFind = new Appointment(patientString, "03/25/2022", "9:30", "MERCER");
        Appointment apptToDelete = schedule.findPatient(simApptToFind);
        while(apptToDelete != null){
            schedule.remove(apptToDelete);
            apptToDelete = schedule.findPatient(simApptToFind);
        }
        //schedule.cancelAll(apptToDelete);
        Patient delPtnt = new Patient(patientString);
        System.out.println("All appointments for " + delPtnt.toString() + " have been canceled.");
    }

    private boolean getAction(String [] command, Schedule schedule){
        String cmd = command[0];
        boolean ret = true;
        if(cmd.equals(BOOK)){
            bookAppt(command, schedule);
        }else if(cmd.equals(CANCEL)){
            cancelAppt(command, schedule);
        }else if(cmd.equals(CANCEL_PATIENT_ALL)){
            cancelAllAppt(command, schedule);
        }else if(cmd.equals(DISPLAY_ALL)){
            schedule.print();
        }else if(cmd.equals(DISPLAY_ALL_BY_ZIP)){
            schedule.printByZip();
        }else if(cmd.equals(DISPLAY_ALL_BY_PTNT)){
            schedule.printByPatient();
        }else if(cmd.equals(QUIT)){
            System.out.println("Kiosk Session Ended.");
            ret = false;
        }else{
            System.out.println("Invalid Command!");
        }
        return ret;
    }

    public void run(){
        System.out.println("Kiosk running. Ready to process transactions.");
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        Schedule schedule = new Schedule(); // method also take this
        while(loop){
           String command = sc.nextLine();
           String [] commandSplit = command.split(" ");
           loop = getAction(commandSplit, schedule);
        }
    }
}
