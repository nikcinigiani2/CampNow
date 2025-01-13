package BusinessLogic.Service;

import ORM.UserDAO;
import Model.User;

import java.sql.SQLException;

public class UserService {
    UserDAO userDAO;
    User user;

    //TODO implementare i servizi relativi (ReservationService)

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;

        user = new User();
    }

    public boolean checkCredentials(String cf, String psw){
        try{
            boolean check = userDAO.checkCredentials(cf, psw);
            return check;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void login(String cf) throws SQLException {
        user = userDAO.selectUserByCF(cf);

        //TODO caricare eventuali servizi a lui collegati
        //es: collectionService.setUser(user);
        //        collectionService.getAllCollection();
        //
        //        wishlistService.setUser(user);
        //        wishlistService.getAllWishlist();
        //
        //        matchService.setUser(user);
        //        matchService.getMatches();
        //
        //        playerService.setUser(user);
        //        playerService.getAllPlayers();

    }


    public User getCurrentUser(){
        return user;
    }

    public void register (String cf, String name, String surname, int phoneNumber, String birthdate, String email, String psw){
        try{
            userDAO.addUser(cf, name, surname, phoneNumber, birthdate, email, psw);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean checkEmailAlreadyUsed(String email) {
        try {
            return userDAO.emailAlreadyUsed(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
