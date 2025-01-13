package BusinessLogic.Service;

import ORM.*;
import Model.User;

import java.security.Provider;

public class ServiceManager {
    private static ServiceManager instance;

    private UserService userService;
    private User user;

    private ServiceManager(){
        UserDAO userDAO = new UserDAO();
        userService = new UserService(userDAO);
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
