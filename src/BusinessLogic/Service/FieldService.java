package BusinessLogic.Service;

import Model.Club;
import Model.Reservation;
import ORM.FieldDAO;
import Model.Field;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class FieldService {
    private FieldDAO fieldDAO;

    private Field field;

    private Club club;

    public FieldService(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }


    public boolean addField(int id, String clubid, int number, String soil, boolean lights, boolean lockerroom, int price, Time startTime, Time endTime) {
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


    public ArrayList<Field> getAllFields() {
        try{
            ResultSet rs = fieldDAO.getAllFields();
            ArrayList<Field> fields = new ArrayList<>();
            if(rs!= null){
                while (rs.next()) {
                    int id = rs.getInt(("id"));
                    String clubid = rs.getString("clubid");
                    int number = rs.getInt("number");
                    String soil = rs.getString("soil");
                    boolean lights = rs.getBoolean("lights");
                    boolean lockeeroom = rs.getBoolean("lockerroom");
                    int price = rs.getInt("price");
                    Time starttime = rs.getTime("starttime");
                    Time endtime = rs.getTime("endtime");
                    Field field = new Field(id, clubid, number, soil, lights, lockeeroom, price, starttime, endtime);
                    fields.add(field);
                }
            }
            return fields;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /*
        column = "clubid", "number", "soil", "lights", "lockerroom", "price"
        value = valori dei relativi campi
     */
    public ResultSet getFields(String column, Object value) {
        try{
            ResultSet rs = fieldDAO.getFields(column, value);
            ArrayList<Field> fields = new ArrayList<>();
            if(rs!= null){
                while (rs.next()) {
                    int id = rs.getInt(("id"));
                    String clubid = rs.getString("clubid");
                    int number = rs.getInt("number");
                    String soil = rs.getString("soil");
                    boolean lights = rs.getBoolean("lights");
                    boolean lockeeroom = rs.getBoolean("lockerroom");
                    int price = rs.getInt("price");
                    Time starttime = rs.getTime("starttime");
                    Time endtime = rs.getTime("endtime");
                    Field field = new Field(id, clubid, number, soil, lights, lockeeroom, price, starttime, endtime);
                    fields.add(field);
                }
                club.loadFields(fields);
            }
            return rs;
        }catch(SQLException e){
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
            if(rs!= null){
                while (rs.next()) {
                    int number = rs.getInt("number");
                    String soil = rs.getString("soil");
                    boolean lights = rs.getBoolean("lights");
                    boolean lockerroom = rs.getBoolean("lockerroom");
                    int price = rs.getInt("price");
                    Time starttime = rs.getTime("starttime");
                    Time endtime = rs.getTime("endtime");
                    field = new Field(id, clubid, number, soil, lights, lockerroom, price, starttime, endtime);

                }
            }
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

    public boolean updateField(int id, String clubid, int number, String soil, boolean lights, boolean lockerroom, int price, Time startTime, Time endTime){
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
