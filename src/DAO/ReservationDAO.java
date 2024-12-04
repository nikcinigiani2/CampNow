package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {
    public ResultSet getAllReservations() throws SQLException{
        String query = "SELECT * FROM reservations";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        return ps.executeQuery();
    }

    public void addReservation(String clubid, int fieldid, String usercf, String date, String startrent, String endrent) throws SQLException{
        if(checkDisponibility(fieldid, clubid, date, startrent, startrent)){
            String query = "insert into reservations (clubid, fieldid, usercf, date, startrent, endrent) " +
                    "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1, clubid);
            ps.setInt(2, fieldid);
            ps.setString(3, usercf);
            ps.setString(4, date);
            ps.setString(5, startrent);
            ps.setString(6, endrent);
            ps.executeUpdate();
        }
    }

    //TODO: deleteReservation, updateReservation,
    // selectReservationByID, selectReservationByUser, selectReservationByClub, selectReservationByField,
    // selectReservationByDate


    public boolean checkDisponibility(int fieldid, String clubid, String date, String startrent, String endrent) throws SQLException{
        String query1 = "SELECT count(*) FROM fields f WHERE f.id = ? AND f.clubid = ? AND f.starttime <= ? AND f.endtime >= ?";
        PreparedStatement ps1 = ManagerDAO.getConnection().prepareStatement(query1);
        ps1.setInt(1, fieldid);
        ps1.setString(2, clubid);
        ps1.setString(3, startrent);
        ps1.setString(4, endrent);
        ResultSet rs1 = ps1.executeQuery();
        if(rs1.next()){
            if(rs1.getInt(1) >= 1) {
                String query2 = "select count(*) from reservations r where r.date = ? and r.fieldid = ? and r.clubid = ?" +
                        "and (r.startrent <= ? and r.endrent >= ?)";
                PreparedStatement ps2 = ManagerDAO.getConnection().prepareStatement(query2);
                ps2.setString(1, date);
                ps2.setInt(2, fieldid);
                ps2.setString(3, clubid);
                ps2.setString(4, endrent);
                ps2.setString(5, startrent);
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
