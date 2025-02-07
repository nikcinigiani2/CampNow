package ORM;

import java.sql.*;

import Model.User;

public class UserDAO {
    public User selectUserByCF(String cf) throws SQLException {
        String query = "SELECT * FROM users WHERE cf = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, cf);
        ResultSet rs = ps.executeQuery();
        User user = null;

        if(rs.next()) {
            user = new User(cf,
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("phoneNumber"),
                    rs.getString("birthdate"),
                    rs.getString("email"),
                    rs.getString("password"));
        }
        return user;
    }

    public boolean checkCredentials(String cf, String password) throws SQLException{
        String query ="SELECT count(*) FROM users WHERE cf = ? AND password = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, cf);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            return rs.getInt(1)==1; //se la query ritorna 1, vuol dire che
                                                // ha trovato la corrispondenza nel db
        }
        return false;
    }

    public void deleteUser(String cf) throws SQLException {
        String query = "DELETE FROM users WHERE cf = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, cf);
        ps.executeUpdate();
    }

    public void updateUser(String cf, String name, String surname, int phoneNumber, String birthdate, String email, String password) throws SQLException{
        if(!cfAlreadyUsed(cf)){
            String query = "UPDATE users SET name = ?, surname = ?, phoneNumber = ?, birthdate = ?, email = ?, password = ? WHERE cf = ?";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setInt(3,phoneNumber);
            ps.setString(4,birthdate);
            ps.setString(5,email);
            ps.setString(6,password);
            ps.setString(7,cf);
            ps.executeUpdate();
        }
    }

    public void addUser(String cf, String name, String surname, int phoneNumber, Date birthdate, String email, String password) throws SQLException{
        if(!cfAlreadyUsed(cf)){
            String query = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1,cf);
            ps.setString(2,name);
            ps.setString(3,surname);
            ps.setInt(4,phoneNumber);
            ps.setDate(5,birthdate);
            ps.setString(6,email);
            ps.setString(7,password);
            ps.executeUpdate();
        }
    }

    public ResultSet getPasswordByEmail(String email) throws SQLException{
        String query = "SELECT password FROM users WHERE email = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public String getNameByCF(String cf) throws SQLException{
        String query = "SELECT name FROM users WHERE cf = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,cf);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("name");
        }
        return "null";
    }

    public String getSurnameByCF(String cf) throws SQLException{
        String query = "SELECT surname FROM users WHERE cf = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,cf);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("surname");
        }
        return "null";
    }

    public boolean cfAlreadyUsed(String cf) throws SQLException {
        String query="SELECT COUNT(*) FROM users WHERE cf = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,cf);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) >= 1;
        }
        return false;
    }

    public boolean emailAlreadyUsed(String email) throws SQLException {
        String query="SELECT COUNT(*) FROM users WHERE email = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) >= 1;
        }
        return false;
    }
}