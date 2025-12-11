package store.models;

import java.util.HashMap;

public class Order {
    //Enums
    public enum Status {
        CREATED,
        PAYMENT_PENDING,
        PAYED,
        SENT,
        DELIVERED,
        FINISHED,
        CANCELED,
        RETURNED
    }

    //Fundamentals fields
    private final char[] id; //Internal Order ID
    private final long createdAt;
    private Status currentStatus;
    private Client client;
    private HashMap<char[], ItemOrder> items;

    //Constructor
    public Order(char[] id, long timestamp, Client client) {
        this.id = id;
        this.createdAt = timestamp;
        this.client = client;
        this.currentStatus = Status.CREATED;
        this.items = new HashMap<>();
    }

    /* Getters and Setters */
    public char[] getId() {
        return id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getCurrentStatus() {
        return currentStatus.toString();
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public HashMap<char[], ItemOrder> getItems() {
        return items;
    }

    /* Management methods */
    public void addItem(ItemOrder item) {
        this.items.put(item.getId(), item);
    }

    public void removeItem(char[] itemID) {
        this.items.remove(itemID);
    }

    public double getTotal() {
        double total = 0.0;
        for (ItemOrder item : items.values()) {
            total += item.getSubtotal();
        }
        return total;
    }
}
