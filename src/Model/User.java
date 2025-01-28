package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String cf;
    private String name;
    private String surname;
    private int phoneNumber;
    private String birthdate;
    private String email;
    private String password;

    private ArrayList <Reservation> reservations;

    public User (String cf, String name, String surname, int phoneNumber, String birthdate, String email, String password) {
        this.cf = cf;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        reservations = new ArrayList<Reservation>();
    }

    public User(){
        reservations = new ArrayList<Reservation>();
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void removeResevation(int reservationID){
        for(Reservation r: reservations){
            if(r.getId() == reservationID){
                reservations.remove(r);
            }
        }
    }

    public int numberReservations(){
        return reservations.size();
    }

    public boolean alreadyReservationLoaded(int reservationID){
        boolean loaded = false;
        for(Reservation r: reservations){
            if(r.getId() == reservationID) {
                loaded = true;
            }
        }
        return loaded;
    }

    public String getCf(){
        return cf;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }


}