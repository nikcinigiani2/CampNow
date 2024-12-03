package Model;

public class User {
    private String cf;
    private String name;
    private String surname;
    private int phoneNumber;
    private String birthdate;
    private String email;
    private String password;

    // TODO: oggetto reservations

    public User (String cf, String name, String surname, int phoneNumber, String birthdate, String email, String password){
        this.cf = cf;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;

        //TODO: reservations
    }

    public String getCf(){
        return cf;
    }
}
