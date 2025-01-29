package BusinessLogic.Service;

import Model.Club;
import Model.User;
import ORM.ReservationDAO;
import Model.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ReservationService {
    private ReservationDAO reservationDAO;

    private Reservation reservation;

    private User user;
    private Club club;

    public ReservationService(ReservationDAO reservationDAO){
        this.reservationDAO = reservationDAO;
    }

    public ResultSet getAllReservation(){
        try{
            ResultSet rs = reservationDAO.getAllReservations(user.getCf());
            ArrayList<Reservation> reservations = new ArrayList<>();
            if(rs!= null){
                while (rs.next()) {
                    int id = rs.getInt(("id"));
                    String clubid = rs.getString("clubid");
                    int fieldid = rs.getInt("fieldid");
                    String usercf = rs.getString("usercf");
                    String date = rs.getString("date");
                    String startRent = rs.getString("startRent");
                    String endRent = rs.getString("endRent");
                    Reservation reservation = new Reservation(id, clubid, fieldid, usercf,date, startRent, endRent, reservationDAO.getDateTimeReservation(id));
                    reservations.add(reservation);
                }

                user.loadReservations(reservations);
            }
            return rs;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Reservation getReservationById(int id){
        try {
            ResultSet rs = reservationDAO.getReservationByID(id);
            rs.next();
            String clubid = rs.getString("clubid");
            int fieldid = rs.getInt("fieldid");
            String usercf = rs.getString("usercf");
            String date = rs.getString("date");
            String startRent = rs.getString("startRent");
            String endRent = rs.getString("endRent");
            Reservation reservation = new Reservation(id, clubid, fieldid, usercf,date, startRent, endRent, reservationDAO.getDateTimeReservation(id));
            return reservation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // column = "userid", "clubid", "fieldid", "date" |  value = valore di quel preciso dato
    public ResultSet getReservationBy(String column, Object value){
        try {
            return reservationDAO.getReservationsBy(column, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addReservation(String clubid, int fieldid, String usercf, String date, String startRent, String endRent){
        try{
            reservationDAO.addReservation(clubid, fieldid, usercf, date, startRent, endRent);
            int id = reservationDAO.getMostRecentReservationId(usercf);
            reservation = new Reservation(id, clubid ,fieldid, usercf, date, startRent, endRent, reservationDAO.getDateTimeReservation(id));

            user.addReservation(reservation);
            club.addReservation(reservation);
            return true;
        }catch(SQLException e){
            System.err.println("Errore durante l'aggiunta della prenotazione: " +e.getMessage());
            return false;
        }
    }

    public boolean updateReservation(int id, String clubid, int fieldid, String date, String startRent, String endRent){
        try{
            reservationDAO.updateReservation(id, clubid, fieldid, date, startRent, endRent);
            return true;
        }catch(SQLException e){
            System.err.println("Errore durante l'aggiornamento della prenotazione: " +e.getMessage());
            return false;
        }
    }

    public boolean deleteReservation(int id){
        try{
            reservationDAO.deleteReservation(id);
            user.removeResevation(id);
            club.removeReservation(id);
            return true;
        }catch(SQLException e){
            System.err.println("Errore durante la rimozione della prenotazione: " +e.getMessage());
            return false;
        }
    }

    public void setClub(Club club){
        this.club = club;
    }

    public void setUser(User user){
        this.user = user;
    }
}