package Model;

public class Reservation {
    private int id;
    private String clubid;
    private String fieldid;
    private String usercf;
    private String date;
    private String startrent;
    private String endrent;


    public Reservation(int id, String clubid, String fieldid, String usercf, String date, String startrent, String endrent) {
        this.id = id;
        this.clubid = clubid;
        this.fieldid = fieldid;
        this.usercf = usercf;
        this.date = date;
        this.startrent = startrent;
        this.endrent = endrent;
    }

    public int getId(){
        return id;
    }
}
