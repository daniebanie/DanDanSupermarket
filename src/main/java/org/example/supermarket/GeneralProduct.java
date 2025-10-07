package org.example.supermarket;

public class GeneralProduct extends Product {

    // Constructor
    public GeneralProduct(String name, double price, String category) {
        super(name, price, category);
    }

    @Override
    public String toString() {
        return "GeneralProduct{name='" + getName() + "', price=" + getPrice() +
                ", category='" + getCategory() + "'}";
    }
}
