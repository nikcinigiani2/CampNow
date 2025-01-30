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
}
