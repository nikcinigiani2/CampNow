package Model;

import java.util.ArrayList;

public class Club {
    private String id;
    private String name;
    private String city;
    private String address;
    private int phoneNumber;
    private String email;
    private String password;

    private ArrayList <Field> fields;

    //TODO: arraylist di reservations

    public Club(String id, String name, String city, String address, int phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        fields = new ArrayList<Field>();
    }

    public void addField(Field field){
        fields.add(field);
    }

    public void removeField(int fieldId){
        for(Field f: fields){
            if(f.getId() == fieldId){
                fields.remove(f);
            }
        }
    }

    public int numberFields(){
        return fields.size();
    }

    public boolean alreadyFieldLoaded(int fieldId){
        boolean loaded = false;
        for(Field f: fields){
            if(f.getId() == fieldId) {
                loaded = true;
            }
        }
        return loaded;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
