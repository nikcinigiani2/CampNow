package BusinessLogic.Service;

import ORM.*;

public class ServiceManager {
    private static ServiceManager instance;
    private ClubService clubService;
    private UserService userService;
    private FieldService fieldService;
    private ReservationService reservationService;

    private ServiceManager(){
        UserDAO userDAO = new UserDAO();
        ClubDAO clubDAO = new ClubDAO();
        FieldDAO fieldDAO = new FieldDAO();
        ReservationDAO reservationDAO = new ReservationDAO();

        reservationService = new ReservationService(reservationDAO);
        fieldService = new FieldService(fieldDAO);
        userService = new UserService(userDAO, reservationService);
        clubService = new ClubService(clubDAO, fieldService, reservationService);
    }

    public static ServiceManager getInstance(){
        if (instance == null)
            instance = new ServiceManager();
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }

    public ClubService getClubService() {
        return clubService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }

    public FieldService getFieldService() {
        return fieldService;
    }
}