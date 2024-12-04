package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldDAO {

    public ResultSet getAllFields() throws SQLException{
        String query = "SELECT * FROM fields";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        return ps.executeQuery();
    }

    public ResultSet getFieldById(int id, String clubid) throws SQLException {
        String query = "SELECT * FROM fields WHERE id = ? and clubid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, clubid);
        return ps.executeQuery();
    }

    public ResultSet getFieldsByClub(String clubid) throws SQLException{
        String query = "SELECT * FROM fields WHERE clubid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, clubid);
        return ps.executeQuery();
    }

    public ResultSet getFieldByNumber(int number) throws SQLException {
        String query = "SELECT * FROM fields WHERE number = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, number);
        return ps.executeQuery();
    }

    public ResultSet getFieldBySoil(String soil) throws SQLException {
        String query = "SELECT * FROM fields WHERE soil = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, soil);
        return ps.executeQuery();
    }

    public ResultSet getFieldsWithLights()throws SQLException{
        String query = "SELECT * FROM fields WHERE lights = true";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        return ps.executeQuery();
    }

    public ResultSet getFieldsWithLockerRoom()throws SQLException{
        String query = "SELECT * FROM fields WHERE lockerroom = true";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        return ps.executeQuery();
    }

    public ResultSet getFieldsOrderByPrice() throws SQLException{
        String query = "SELECT * FROM fields ORDER BY price ASC";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        return ps.executeQuery();
    }
}