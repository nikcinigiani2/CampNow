package BusinessLogic.Service;

import ORM.UserDAO;
import Model.User;

import java.sql.Date;
import java.sql.SQLException;

public class UserService {
    UserDAO userDAO;
    User user;

    ReservationService reservationService;

    public UserService(UserDAO userDAO, ReservationService reservationService){
        this.userDAO = userDAO;
        this.reservationService = reservationService;
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
        reservationService.setUser(user);
    }


    public User getCurrentUser(){
        return user;
    }

    public void register (String cf, String name, String surname, int phoneNumber, Date birthdate, String email, String psw){
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