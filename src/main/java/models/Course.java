package models;

import java.text.DateFormat;

public class Course {
    private String name;
    private String notes;
    private String timetable;
    private String altTimetable;

    private int numOfSeats;
    private int remainingSeats;


    public Course() {
        this.name = "Unknown Course";

        this.numOfSeats = 60;
        this.notes = "";
    };

    public Course(String name, int remainingSeats) {
        this.name = name;
        this.numOfSeats = 60;
        this.remainingSeats = remainingSeats;
        this.notes = "";
        this.timetable = "";
        this.altTimetable = "";
    }

    public Course(String name, int numOfSeats, int remainingSeats) {
        this.name = name;
        this.numOfSeats = numOfSeats;
        this.remainingSeats = remainingSeats;
        this.notes = "";

        this.timetable = "";
        this.altTimetable = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return remainingSeats > 0 ? "Open" : "Full";
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public String getNotes() {
        if (remainingSeats == 0) {
            return "Will be referred to waiting list";
        }
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setTimetable(String timetable){
        this.timetable = timetable;
    }
    public String getTimetable(){
        if(this.timetable != "") {
            return "Timetable: " + this.timetable;
        }
        return "";
    }

    public void setAltTimetable(String altTimetable){
        this.altTimetable = altTimetable;
    }
    public String getAltTimetable(){
        if(this.altTimetable != "") {
            return "Alternative Timetable: " + this.altTimetable;
        }
        return "";
    }

}
