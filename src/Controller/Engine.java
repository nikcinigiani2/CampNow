package Controller;

import BusinessLogic.Service.*;
import Model.Reservation;
import Model.User;
import Model.Club;
import Model.Field;
import View.ReserveField;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Time;


public class Engine {
    private static Engine instance;
    private User user;
    private Club club;
    private ServiceFactory sf;

    private Engine(){
        sf = ServiceFactory.getInstance();
    }
    public User getUser(){
        return user;
    }
    public Club getClub(){
        return club;
    }

    public static Engine getInstance(){
        if(instance == null)
            instance = new  Engine();
        return instance;
    }

    // USER FUNCTION -----------------------
    public boolean loginUser(String cf, String password) {
        boolean logged = false;
        try {
            UserService userService = (UserService) sf.getService(sf.USER_SERVICE);
            if (userService.checkCredentials(cf, password)) {
                userService.login(cf);
                this.user = userService.getCurrentUser();
                logged = true;
            } else {
                System.out.println("Credenziali errate");
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il login: " + e.getMessage());
        }
        return logged;
    }

    public boolean registerUser(String cf, String name, String surname, int phoneNumber, Date birthdate, String email, String psw){
        boolean registered = false;
        UserService userService = (UserService) sf.getService(sf.USER_SERVICE);
        if(!userService.checkCfAlreadyUsed(cf) && !userService.checkEmailAlreadyUsed(email)){
            userService.register(cf, name, surname, phoneNumber, birthdate, email, psw);
            this.user = userService.getCurrentUser();
            registered = true;
        }
        else {
            System.out.println("Utente già registrato");
        }
        return registered;
    }

    public String getNameByCF(String cf){
        UserService us = (UserService) sf.getService(sf.USER_SERVICE);
        return us.getNameByCF(cf);
    }

    public String getSurnameByCF(String cf){
        UserService us = (UserService) sf.getService(sf.USER_SERVICE);
        return us.getSurnameByCF(cf);
    }


    public void userLogout() {
        user=null;
    }


    // CLUB FUNCTION -----------------------

    public boolean loginClub(String email, String password) {
        boolean logged = false;
        try {
            ClubService clubService = (ClubService) sf.getService(sf.CLUB_SERVICE);
            if (clubService.checkCredentials(email, password)) {
                clubService.login(email);
                this.club = clubService.getCurrentClub();
                logged = true;
            } else {
                System.out.println("Credenziali errate");
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il login: " + e.getMessage());
        }
        return logged;
    }

    public boolean registerClub(String id, String name, String city, String address, int phoneNumber, String email, String psw){
        boolean registered = false;
        ClubService clubService = (ClubService) sf.getService(sf.CLUB_SERVICE);
        if(!clubService.checkEmailAlreadyUsed(email) && !clubService.checkIdAlreadyUsed(id)){
            clubService.register(id, name, city, address, phoneNumber, email, psw);
            this.club = clubService.getCurrentClub();
            registered = true;
        }
        else {
            System.out.println("Club già registrato");
        }
        return registered;
    }

    public String getNameById(String id){
        ClubService cs = (ClubService) sf.getService(sf.CLUB_SERVICE);
        return cs.getNameById(id);
    }

    public String getCityById(String id){
        ClubService cs = (ClubService) sf.getService(sf.CLUB_SERVICE);
        return cs.getCityById(id);
    }

    public String getAddressById(String id){
        ClubService cs = (ClubService) sf.getService(sf.CLUB_SERVICE);
        return cs.getAddressById(id);
    }

    public void clubLogout() {
        club=null;
    }

    // FIELD FUNCTIONS -----------------------

    public ArrayList<Field> getAllFields(){
        FieldService fs = (FieldService) sf.getService(sf.FIELD_SERVICE);
        return fs.getAllFields();
    }

    public Field getFieldByID(int fieldId, String clubId){
        FieldService fs = (FieldService) sf.getService(sf.FIELD_SERVICE);
        return fs.getFieldById(fieldId, clubId);
    }

    public boolean addField(int id, int number, String soil, boolean lights, boolean lockerroom, int price, Time startTime, Time endTime) {
        FieldService fs = (FieldService) sf.getService(sf.FIELD_SERVICE);
        return fs.addField(id, getClub().getId(), number, soil, lights, lockerroom, price, startTime, endTime);
    }

    public boolean updateField(int id, int number, String soil, boolean lights, boolean lockerroom, int price, Time startTime, Time endTime){
        FieldService fs = (FieldService) sf.getService(sf.FIELD_SERVICE);
        return fs.updateField(id, getClub().getId(), number, soil, lights, lockerroom, price, startTime, endTime);
    }

    public boolean deleteField(int id){
        FieldService fs = (FieldService) sf.getService(sf.FIELD_SERVICE);
        return fs.deleteField(id, getClub().getId());
    }


    // RESERVATION FUNCTIONS -----------------------


    public boolean addReservation(String clubid, int fieldid, Date date, Time startTime, Time endTime){
        ReservationService rs = (ReservationService) sf.getService(sf.RESERVATION_SERVICE);
        return rs.addReservation(clubid, fieldid, getUser().getCf(), date, startTime, endTime);
    }

    public Reservation getReservationByID(int reservationId){
        ReservationService rs = (ReservationService)  sf.getService(sf.RESERVATION_SERVICE);
        return rs.getReservationById(reservationId);
    }

    public boolean updateReservation(int id, String clubid, int fieldid, Date date, Time startRent, Time endRent){
        ReservationService rs = (ReservationService) sf.getService(sf.RESERVATION_SERVICE);
        return rs.updateReservation(id, clubid, fieldid, date, startRent, endRent);
    }

    public boolean deleteReservation(int id){
        ReservationService rs = (ReservationService) sf.getService(sf.RESERVATION_SERVICE);
        return rs.deleteReservation(id);
    }


}

