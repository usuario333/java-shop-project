package store.models;

public class Product {
    //Fundamentals fields
    private final char[] id; //Internal Product ID
    private String reference;
    private String name;
    private double price;
    private ProductCategory category;

    //Constructor
    public Product(char[] id, String reference, String name, double price, ProductCategory category) {
        this.id = id;
        this.reference = reference;
        this.name = name;
        this.price = price;
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
