package BusinessLogic.Service;

import ORM.*;
import Model.User;
import Model.Club;

public class ServiceManager {
    private static ServiceManager instance;

    private ClubService clubService;
    private Club club;
    private UserService userService;
    private User user;

    private ServiceManager(){
        UserDAO userDAO = new UserDAO();
        //TODO aggiungere ResercationService, ClubService e FieldService
        // userService = new UserService(userDAO, reservationService);
    }

    public static ServiceManager getInstance(){
        if (instance == null)
            instance = new ServiceManager();
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }
}