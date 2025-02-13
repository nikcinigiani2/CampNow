package BusinessLogic.Service;

import ORM.ClubDAO;
import Model.Club;

import java.sql.SQLException;

public class ClubService {

    private ClubDAO clubDAO;
    private Club club;

    private FieldService fieldService;
    private ReservationService reservationService;

    public ClubService (ClubDAO clubDAO, FieldService fieldService, ReservationService reservationService){
        this.clubDAO = clubDAO;
        this.fieldService = fieldService;
        this.reservationService = reservationService;
        club = new Club();
    }

    public boolean checkCredentials(String email, String psw){
        try{
            boolean check = clubDAO.checkCredentials(email, psw);
            return check;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void login(String email) throws SQLException {
        club = clubDAO.selectClubByEmail(email);

        fieldService.setClub(club);
        fieldService.getFields("clubid", club.getId());

        reservationService.setClub(club);
        reservationService.getAllReservation(this);
    }

    public Club getCurrentClub(){
        return club;
    }

    public String getNameById(String id){
        try{
            String name = clubDAO.getNamedById(id);
            return name;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String getCityById(String id){
        try{
            String city = clubDAO.getCityById(id);
            return city;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public String getAddressById(String id){
        try{
            String address = clubDAO.getAddressdById(id);
            return address;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void register(String id, String name, String city, String address, int phoneNumber, String email, String psw){
        try{
            clubDAO.addClub(id, name, city, address, phoneNumber, email, psw);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean checkEmailAlreadyUsed(String email) {
        try {
            return clubDAO.emailAlreadyUsed(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIdAlreadyUsed(String id) {
        try {
            return clubDAO.idAlreadyUsed(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}