package Model;

public class Club {
    private String id;
    private String name;
    private String city;
    private String address;
    private int phoneNumber;
    private String email;
    private String password;

    //TODO: arraylist di reservations
    //TODO: arraylist di fields

    public Club(String id, String name, String city, String address, int phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;

        //TODO: arraylist di reservations
        //TODO: arraylist di fields
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
