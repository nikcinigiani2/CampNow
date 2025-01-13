package BusinessLogic.Service;

import ORM.ClubDAO;
import Model.Club;

import java.sql.SQLException;

public class ClubService {

    ClubDAO clubDAO;
    Club club;

    public ClubService (ClubDAO clubDAO){
        this.clubDAO = clubDAO;

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

        //TODO caricare eventuali servizi a lui collegati
        //es: campi fieldService.setClub(club);
        //          fieldService.getAllFields();

    }

}
