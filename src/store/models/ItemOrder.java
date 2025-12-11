package store.models;

public class ItemOrder {
    //Fundamentals fields
    private final char[] id; //Internal Item Oder ID
    private Product product;
    private double price;
    private int amount;

    //Constructor
    public ItemOrder(char[] id, Product product, double price, int amount) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.amount = amount;
    }

    /* Getters and Setters */
    public char[] getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /* Management methods */
    public double getSubtotal() {
        return this.price * this.amount;
    }
}
