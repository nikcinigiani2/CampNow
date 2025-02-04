package BusinessLogic.Service;

import Controller.Engine;
import Model.Club;
import Model.User;
import ORM.ReservationDAO;
import Model.Reservation;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


public class ReservationService {
    private ReservationDAO reservationDAO;

    private Reservation reservation;

    private User user;
    private Club club;

    public ReservationService(ReservationDAO reservationDAO){
        this.reservationDAO = reservationDAO;
    }

    public ResultSet getAllReservation(Object userType){
        try{
            ResultSet rs = null;
            if(userType instanceof UserService){
                if(user != null){
                    rs = reservationDAO.getAllReservationsUser(user.getCf());
                }
            }
            else{
                if(club != null){
                    rs = reservationDAO.getAllReservationsClub(club.getId());
                }
            }

            ArrayList<Reservation> reservations = new ArrayList<>();
            if(rs!= null){
                while (rs.next()) {
                    int id = rs.getInt(("id"));
                    String clubid = rs.getString("clubid");
                    int fieldid = rs.getInt("fieldid");
                    String usercf = rs.getString("usercf");
                    Date date = rs.getDate("date");
                    Time startRent = rs.getTime("startRent");
                    Time endRent = rs.getTime("endRent");
                    Reservation reservation = new Reservation(id, clubid, fieldid, usercf,date, startRent, endRent, reservationDAO.getDateTimeReservation(id));
                    reservations.add(reservation);
                }

                if(userType instanceof UserService)
                    user.loadReservations(reservations);
                else
                    club.loadReservations(reservations);
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
            Date date = rs.getDate("date");
            Time startRent = rs.getTime("startRent");
            Time endRent = rs.getTime("endRent");
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

    public boolean addReservation(String clubid, int fieldid, String usercf, Date date, Time startRent, Time endRent){
        boolean added = false;
        try{
            added = reservationDAO.addReservation(clubid, fieldid, usercf, date, startRent, endRent);

            if(added){
                int id = reservationDAO.getMostRecentReservationId(usercf);
                reservation = new Reservation(id, clubid ,fieldid, usercf, date, startRent, endRent, reservationDAO.getDateTimeReservation(id));
                if(user != null)
                    System.out.println("SONO ENTRATO");
                    user.addReservation(reservation);
                if(club != null)
                    club.addReservation(reservation);
            }
            return added;
        }catch(SQLException e){
            System.err.println("Errore durante l'aggiunta della prenotazione: " + e.getMessage());
            return added;
        }
    }

    public boolean updateReservation(int id, String clubid, int fieldid, Date date, Time startRent, Time endRent){
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
            user.removeReservation(id);
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