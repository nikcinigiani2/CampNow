package Model;

public class Reservation {
    private int id;
    private String clubid;
    private int fieldid;
    private String usercf;
    private String date;
    private String startrent;
    private String endrent;
    private String datetime;


    public Reservation(int id, String clubid, int fieldid, String usercf, String date, String startrent, String endrent, String datetime) {
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

    public String getDate() {
        return date;
    }

    public String getStartrent() {
        return startrent;
    }

    public String getEndrent() {
        return endrent;
    }

    public String getDatetime() {
        return datetime;
    }
}
