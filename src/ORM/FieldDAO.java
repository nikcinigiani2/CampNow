package ORM;

import Model.Field;

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

    public Field selectFieldById(int id, String clubid) throws SQLException {
        String query = "SELECT * FROM fields WHERE id = ? and clubid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, clubid);
        ResultSet rs = ps.executeQuery();
        Field field = null;

        if(rs.next()) {
            field = new Field(id, clubid,
                    rs.getInt("number"),
                    rs.getString("soil"),
                    rs.getBoolean("lights"),
                    rs.getBoolean("lockerroom"),
                    rs.getInt("price"),
                    rs.getString("starttime"),
                    rs.getString("endtime"));
        }
        return field;
    }

    /*
        column = "clubid", "number", "soil", "lights", "lockerroom", "price"
        value = valori dei relativi campi
     */
    public ResultSet getFields(String column, Object value) throws SQLException {
        String query;

        if (!"price".equals(column)) {
            query = "SELECT * FROM fields WHERE " + column + " = ?";
        } else {
            query = "SELECT * FROM fields ORDER BY price ASC";
        }

        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);

        if (!"price".equals(column)) {
            if (value instanceof String) {
                ps.setString(1, (String) value);
            } else if (value instanceof Integer) {
                ps.setInt(1, (Integer) value);
            } else {
                ps.setBoolean(1, (Boolean) value);
            }
        }
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