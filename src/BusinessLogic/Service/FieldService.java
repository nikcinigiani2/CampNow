package BusinessLogic.Service;

import Model.Club;
import ORM.FieldDAO;
import Model.Field;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldService {
    private FieldDAO fieldDAO;

    private Field field;

    private Club club;

    public FieldService(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }


    public boolean addField(int id, String clubid, int number, String soil, boolean lights, boolean lockerroom, int price, String startTime, String endTime) {
        try {
            fieldDAO.addField(id, clubid, number, soil, lights, lockerroom, price, startTime, endTime);
            field = new Field(id, clubid, number, soil, lights, lockerroom, price, startTime, endTime);

            club.addField(field);
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del campo: " + e.getMessage());
            return false;
        }
    }


    public ResultSet getAllFields() {
        try {
            return fieldDAO.getAllFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        column = "clubid", "number", "soil", "lights", "lockerroom", "price"
        value = valori dei relativi campi
     */
    public ResultSet getFields(String column, Object value) {
        try {
            return fieldDAO.getFields(column, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getFieldsOrderbyPrice() {
        try {
            return fieldDAO.getFieldsOrderByPrice();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Field getFieldById(int id, String clubid){
        try{
            ResultSet rs = fieldDAO.getFieldById(id, clubid);
            rs.next();
            int number = rs.getInt("number");
            String soil = rs.getString("soil");
            boolean lights = rs.getBoolean("lights");
            boolean lockerroom = rs.getBoolean("lockerroom");
            int price = rs.getInt("price");
            String startTime = rs.getString("startTime");
            String endTime = rs.getString("endTime");
            Field field = new Field(id, clubid, number, soil, lights, lockerroom, price, startTime, endTime);
            return field;
        }catch(SQLException e){
            System.err.println("Errore durante la lettura del campo: "+e.getMessage());
            return null;
        }
    }

    public boolean deleteField(int id, String clubid){
        try {
            fieldDAO.deleteField(id, clubid);
            club.removeField(id);
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione del campo: "+e.getMessage());
            return false;
        }
    }

    public boolean updateField(int id, String clubid, int number, String soil, boolean lights, boolean lockerroom, int price, String startTime, String endTime){
        try{
            fieldDAO.updateField(id, clubid, number, soil, lights, lockerroom, price, startTime, endTime);
            return true;
        }catch(SQLException e){
            System.err.println("Errore durante l'aggiornamento del campo: " +e.getMessage());
            return false;
        }
    }

    public void setClub(Club club){
        this.club = club;
    }

}
