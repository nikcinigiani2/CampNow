package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Club {
    private String id;
    private String name;
    private String city;
    private String address;
    private int phoneNumber;
    private String email;
    private String password;

    private ArrayList <Field> fields;
    private ArrayList <Reservation> reservations;

    public Club(String id, String name, String city, String address, int phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        fields = new ArrayList<Field>();
        reservations = new ArrayList<Reservation>();
    }

    public Club (){
        fields = new ArrayList<Field>();
        reservations = new ArrayList<Reservation>();
    }

    public int numberReservations(){
        return reservations.size();
    }

    public void loadReservations(ArrayList<Reservation> reservations){
        this.reservations = reservations;
    }
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void removeReservation(int reservationID){
        for(Reservation r: reservations){
            if(r.getId() == reservationID){
                reservations.remove(r);
            }
        }
    }

    private boolean alreadyReservationLoaded(int reservationID){
        boolean loaded = false;
        for(Reservation r: reservations){
            if(r.getId() == reservationID) {
                loaded = true;
            }
        }
        return loaded;
    }

    public void addField(Field field){
        fields.add(field);
    }

    public void removeField(int fieldID){
        Iterator<Field> iterator = fields.iterator();
        while (iterator.hasNext()){
            Field f = iterator.next();
            if (f.getId() == fieldID){
                iterator.remove();
                return;
            }
        }
    }

    public void loadFields(ArrayList<Field> fields){
        this.fields = fields;
    }



    public int numberFields(){
        return fields.size();
    }

    public boolean alreadyFieldLoaded(int fieldID){
        boolean loaded = false;
        for(Field f: fields){
            if(f.getId() == fieldID) {
                loaded = true;
            }
        }
        return loaded;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public  ArrayList<Field> getFields(){
        return fields;
    }

    public  ArrayList<Reservation> getReservations(){
        return reservations;
    }

    public Object getAddress() {
        return address;
    }

    public Object getCity() {
        return city;
    }

}