package store.models;

public class Client {
    //Fundamentals fields
    private final char[] id; //Internal Client ID
    private String identification;
    private String fullName;

    //Optional fields
    private String genre;
    private String address;
    private String city;

    //Constructor
    public Client(char[] id, String fullName, String identification) {
        this.id = id;
        this.fullName = fullName;
        this.identification = identification;
    }

    /* Getters and Setters */
    public char[] getId() {
        return id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
