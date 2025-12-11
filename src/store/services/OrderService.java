package store.services;

import java.util.HashMap;

import store.models.ItemOrder;
import store.models.Order;

public class OrderService {
    HashMap<char[], Order> orders;
    
    public OrderService() {
        orders = new HashMap<>();
    }

    public HashMap<char[], Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void addItemOrder(char[] orderId, ItemOrder item) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.addItem(item);
        }
    }
}
