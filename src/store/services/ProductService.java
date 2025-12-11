package store.services;

import java.util.HashMap;

import store.models.Product;

public class ProductService {
    HashMap<char[], Product> products;

    public ProductService() {
        products = new HashMap<>();
    }

    public HashMap<char[], Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }
}
