package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldDAO {
    public void addField(int id, String clubid, int number, String soil, boolean lights, boolean lockerroom, int price, String startTime, String endTime) throws SQLException{
        if(!alreadyFieldLoaded(id, clubid)){
            String query = "INSERT INTO fields VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, clubid);
            ps.setInt(3, number);
            ps.setString(4, soil);
            ps.setBoolean(5, lights);
            ps.setBoolean(6, lockerroom);
            ps.setInt(7, price);
            ps.setString(8, startTime);
            ps.setString(9, endTime);
            ps.executeUpdate();
        }
    }

    public void deleteField(int id, String clubid) throws SQLException{
        String query = "DELETE FROM fields WHERE id = ? AND clubid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, clubid);
    }

    public void updateField(int id, String clubid, int number, String soil, boolean lights, boolean lockerroom, int price, String startTime, String endTime) throws SQLException{
        if(!alreadyFieldLoaded(id, clubid)) {
            String query = "UPDATE fields SET number = ?, soil = ?, lights = ?, lockerroom = ?, price = ?, starttime = ?, endtime = ? WHERE id = ? AND clubid = ?";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setInt(1, number);
            ps.setString(2, soil);
            ps.setBoolean(3, lights);
            ps.setBoolean(4, lockerroom);
            ps.setInt(5, price);
            ps.setString(6, startTime);
            ps.setString(7, endTime);
            ps.setInt(8, id);
            ps.setString(9, clubid);
            ps.executeUpdate();
        }
    }

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

    private boolean alreadyFieldLoaded(int id, String clubid) throws SQLException{
        boolean loaded = false;
        String query = "SELECT * FROM fields WHERE id = ? AND clubid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,id);
        ps.setString(2, clubid);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            loaded = true;
        return loaded;
    }
}