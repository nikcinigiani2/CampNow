package Controller;

import BusinessLogic.Service.*;
import Model.Reservation;
import Model.User;
import Model.Club;
import Model.Field;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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

    public void userLogout() {
        user=null;
    }

    /*
    *
    *
    * logout
    *
     */
    // -------------------------------------


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
            System.out.println("Utente già registrato");
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

    public ArrayList<Field> getAllFields(){
        FieldService fs = (FieldService) sf.getService(sf.FIELD_SERVICE);
        return fs.getAllFields();
    }


    // -------------------------------------



}

