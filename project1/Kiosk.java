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

    private void bookAppt(String [] command, Schedule schedule){
        String patientString = command[1] + " " + command[2] + " " + command[3];
        Date apptDate = new Date(command[4]);
    }

    private void cancelAppt(String [] command, Schedule schedule){

    }

    private void cancelAllAppt(String [] command, Schedule schedule){

    }

    private void  displayAllAppt(String [] command, Schedule schedule){

    }

    private void displayAllApptByZip(String [] command, Schedule schedule){

    }

    private void displayAllApptByPtnt(String [] command, Schedule schedule){

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
            displayAllAppt(command, schedule);
        }else if(cmd.equals(DISPLAY_ALL_BY_ZIP)){
            displayAllApptByZip(command, schedule);
        }else if(cmd.equals(DISPLAY_ALL_BY_PTNT)){
            displayAllApptByPtnt(command, schedule);
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
