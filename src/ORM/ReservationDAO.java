package ORM;


import Model.Reservation;

import java.sql.*;

public class ReservationDAO {

    public ResultSet getAllReservations(String cf) throws SQLException{
        String query = "SELECT * FROM reservations WHERE usercf = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, cf);
        return ps.executeQuery();
    }

    public void addReservation(String clubid, int fieldid, String usercf, Date date, Time startrent, Time endrent) throws SQLException{
        if(checkDisponibility(fieldid, clubid, date, startrent, startrent)){
            String query = "insert into reservations (clubid, fieldid, usercf, date, startrent, endrent) " +
                    "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1, clubid);
            ps.setInt(2, fieldid);
            ps.setString(3, usercf);
            ps.setDate(4, date);
            ps.setTime(5, startrent);
            ps.setTime(6, endrent);
            ps.executeUpdate();
        }
    }

    public int getMostRecentReservationId(String usercf) throws SQLException {
        String query = "SELECT id FROM reservations WHERE usercf = ? ORDER BY datetime DESC LIMIT 1";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, usercf);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public String getDateTimeReservation(int id) throws SQLException{
        String query = "SELECT datetime FROM reservations WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("datetime");
    }


    public void deleteReservation(int id) throws SQLException {
        String query = "delete from reservations where id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void updateReservation(int id, String clubid, int fieldid, Date date, Time startRent, Time endRent) throws SQLException{
        if(checkDisponibility(fieldid, clubid, date, startRent, endRent)){
            String query = "update reservations set date = ?, startrent = ?, endrent = ? where id = ?";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setDate(1, date);
            ps.setTime(2, startRent);
            ps.setTime(3, endRent);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }


    public ResultSet getReservationByID (int id) throws SQLException{
        String query = "SELECT * FROM reservations WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        return ps.executeQuery();
    }

    // column = "userid", "clubid", "fieldid", "date" |  value = valore di quel preciso dato
    // ES: rs = getReservationBy("fieldid", 2);
    public ResultSet getReservationsBy(String column, Object value) throws SQLException {
        String query = "select * from reservations where " + column + " = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);

        if (value instanceof String) {
            ps.setString(1, (String) value);
        } else{
            ps.setInt(1, (int) value);
        }
        return ps.executeQuery();
    }


    public boolean checkDisponibility(int fieldid, String clubid, Date date, Time startrent, Time endrent) throws SQLException{
        String query1 = "SELECT count(*) FROM fields f WHERE f.id = ? AND f.clubid = ? AND f.starttime <= ? AND f.endtime >= ?";
        PreparedStatement ps1 = ManagerDAO.getConnection().prepareStatement(query1);
        ps1.setInt(1, fieldid);
        ps1.setString(2, clubid);
        ps1.setTime(3, startrent);
        ps1.setTime(4, endrent);
        ResultSet rs1 = ps1.executeQuery();
        if(rs1.next()){
            if(rs1.getInt(1) >= 1) {
                String query2 = "select count(*) from reservations r where r.date = ? and r.fieldid = ? and r.clubid = ?" +
                        "and (r.startrent < ? and r.endrent >0 ?)";
                PreparedStatement ps2 = ManagerDAO.getConnection().prepareStatement(query2);
                ps2.setDate(1, date);
                ps2.setInt(2, fieldid);
                ps2.setString(3, clubid);
                ps2.setTime(4, endrent);
                ps2.setTime(5, startrent);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    if(rs2.getInt(1) >= 1)
                        return false;
                    else{
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
}
