package Model;

import java.sql.Time;

public class Field {
    private int id;
    private String clubid;
    private int number;
    private String soil;
    private boolean lights;
    private boolean lockerroom;
    private int price;
    private Time startTime;
    private Time endTime;

    public Field(int id, String clubid, int number, String soil, boolean lights, boolean lockerroom, int price, Time startTime, Time endTime) {
        this.id = id;
        this.clubid = clubid;
        this.number = number;
        this.soil = soil;
        this.lights = lights;
        this.lockerroom = lockerroom;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId(){
        return id;
    }

    public String getClubid(){
        return clubid;
    }

    public Object getNumber() {
        return number;
    }
    public int getPrice(){return price;}

    public String getSoil() {
        return soil;
    }

    public boolean isLights() {
        return lights;
    }

    public boolean isLockerroom() {
        return lockerroom;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public void setLights(boolean lights) {
        this.lights = lights;
    }

    public void setLockerroom(boolean lockerroom) {
        this.lockerroom = lockerroom;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getLightsToString(){
        if(lights)
            return "Si";
        else
            return "No";
    }

    public String getLockerroomToString(){
        if(lockerroom)
            return "Si";
        else
            return "No";
    }
}
