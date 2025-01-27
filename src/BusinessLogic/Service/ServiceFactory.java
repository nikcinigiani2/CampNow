package BusinessLogic.Service;

public class ServiceFactory {
    private static ServiceFactory instance;

    public final int FIELD_SERVICE = 1;
    public final int RESERVATION_SERVICE = 2;
    public final int CLUB_SERVICE = 3;
    public final int USER_SERVICE = 4;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        if (instance == null)
            instance = new ServiceFactory();
        return instance;
    }

    public Object getService(int service){
        switch (service){
            case FIELD_SERVICE:
                return ServiceManager.getInstance().getFieldService();
            case RESERVATION_SERVICE:
                return ServiceManager.getInstance().getReservationService();
            case CLUB_SERVICE:
                return ServiceManager.getInstance().getClubService();
            case USER_SERVICE:
                return ServiceManager.getInstance().getUserService();
            default:
                throw new IllegalArgumentException("Invalid service");
        }
    }
}
