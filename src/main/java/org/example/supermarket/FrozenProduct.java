package org.example.supermarket;

public class FrozenProduct extends Product {

    // Constructor
    public FrozenProduct(String name, double price, String category) {
        super(name, price, category);
    }

    @Override
    public String toString() {
        return "FrozenProduct{name='" + getName() + "', price=" + getPrice() +
                ", category='" + getCategory() + "'}";
    }
}
