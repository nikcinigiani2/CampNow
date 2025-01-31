package Model;

import java.sql.Date;
import java.sql.Time;

public class Reservation {
    private int id;
    private String clubid;
    private int fieldid;
    private String usercf;
    private Date date;
    private Time startrent;
    private Time endrent;
    private String datetime;


    public Reservation(int id, String clubid, int fieldid, String usercf, Date date, Time startrent, Time endrent, String datetime) {
        this.id = id;
        this.clubid = clubid;
        this.fieldid = fieldid;
        this.usercf = usercf;
        this.date = date;
        this.startrent = startrent;
        this.endrent = endrent;
        this.datetime = datetime;
    }

    public int getId(){
        return id;
    }

    public String getClubid() {
        return clubid;
    }

    public int getFieldId(){
        return fieldid;
    }
    public String getUsercf() {
        return usercf;
    }

    public Date getDate() {
        return date;
    }

    public Time getStartrent() {
        return startrent;
    }

    public Time getEndrent() {
        return endrent;
    }

    public String getDatetime() {
        return datetime;
    }
}
