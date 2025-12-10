package store.models;

public class ProductCategory {
    private final char[] id; //Internal Product Category ID
    private String name;

    //Constructor
    public ProductCategory(char[] id, String name) {
        this.id = id;
        this.name = name;
    }

    /* Getters and Setters */
    public char[] getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
