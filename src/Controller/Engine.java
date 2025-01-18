package Controller;

import BusinessLogic.Service.*;
import Model.User;
import Model.Club;




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
    private Club getClub(){
        return club;
    }

    public static Engine getInstance(){
        if(instance == null)
            instance = new  Engine();
        return instance;
    }

    // USER FUNCTION -----------------------
    /*
    *login
    * register
    * logout
    *
     */
    // -------------------------------------


    // CLUB FUNCTION -----------------------

    // -------------------------------------




}

