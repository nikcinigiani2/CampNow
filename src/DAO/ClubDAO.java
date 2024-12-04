package DAO;

import Model.Club;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubDAO {
    public ResultSet getAllClub() throws SQLException{
        String query = "SELECT * FROM clubs";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        return ps.executeQuery();
    }

    public Club selectClubByEmail(String email) throws SQLException {
        String query = "SELECT * FROM clubs WHERE email = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        Club club = null;

        if(rs.next()) {
            club = new Club(rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("city"),
                    rs.getString("address"),
                    rs.getInt("phoneNumber"),
                    email,
                    rs.getString("password"));
        }
        return club;
    }

    public boolean checkCredentials(String email, String password) throws SQLException{
        String query ="SELECT count(*) FROM clubs WHERE email = ? AND password = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            return rs.getInt(1)==1; //se la query ritorna 1, vuol dire che
            // ha trovato la corrispondenza nel db
        }
        return false;
    }

    public void deleteClub(String id) throws SQLException {
        String query = "DELETE FROM clubs WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public void updateClub(String id, String name, String city, String address, int phoneNumber, String email, String password) throws SQLException{
        String query = "UPDATE clubs SET name = ?, city = ?, address = ?, phoneNumber = ?, email = ?, password = ? WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,name);
        ps.setString(2,city);
        ps.setString(3,address);
        ps.setInt(4,phoneNumber);
        ps.setString(5,email);
        ps.setString(6,password);
        ps.setString(7, id);
        ps.executeUpdate();
    }

    public void addClub(String id, String name, String city, String address, int phoneNumber, String email, String password) throws SQLException{
        String query = "INSERT INTO clubs VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3,city);
        ps.setString(4,address);
        ps.setInt(5,phoneNumber);
        ps.setString(6,email);
        ps.setString(7,password);
        ps.executeUpdate();
    }

    public ResultSet getPasswordByEmail(String email) throws SQLException{
        String query = "SELECT password FROM clubs WHERE email = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public boolean emailAlreadyUsed(String email) throws SQLException {
        String query="SELECT COUNT(*) FROM clubs WHERE email = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) >= 1;
        }
        return false;
    }
}