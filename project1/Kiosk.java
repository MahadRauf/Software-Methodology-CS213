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
        if(!apptDate.isValid()){

        }
        if(birthDay.isFutureDate() || ){

        }
        if(apptDate.isPastDate()){

        }
        String [] timeParam = command[5].split(" ");
        Time apptTime = new Time(Integer.parseInt(timeParam[0]), Integer.parseInt(timeParam[1]));

        if(!locationExists(command[6].toUpperCase())){
            System.out.println("Invalid location!");
            return;
        }
        Appointment apptToAdd = new Appointment(patientString, command[4], command[5], command[6]);
        schedule.add(apptToAdd);
    }

    private void cancelAppt(String [] command, Schedule schedule){
    	String patientString = command[1] + " " + command[2] + " " + command[3];
        Date apptDate = new Date(command[4]);
        Date birthDay = new Date(command[1]);
        String [] timeParam = command[5].split(" ");
        Time apptTime = new Time(Integer.parseInt(timeParam[0]), Integer.parseInt(timeParam[1]));

        
        Appointment apptToDelete = new Appointment(patientString, command[4], command[5], command[6]);
        if(schedule.remove(apptToDelete)) {
        	System.out.println("Appointment cancelled");
        }
        else {
        	System.out.println("Appointment not found");
        }
    	
    }

    // take a whatever appointment and send that to some method so we have the patient and then delete all appt with same patient (implement)
    private void cancelAllAppt(String [] command, Schedule schedule){

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
        while(loop){
           String command = sc.nextLine();
           String [] commandSplit = command.split(" ");
           Schedule schedule = new Schedule(); // method also take this
           loop = getAction(commandSplit, schedule);
        }
    }
}
